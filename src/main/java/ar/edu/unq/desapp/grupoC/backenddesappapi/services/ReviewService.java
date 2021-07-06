package ar.edu.unq.desapp.grupoC.backenddesappapi.services;

import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactiveRedisOperations<String, Review> redisTemplate;

    public List<Review> getReviewsByTitle(String title) {
        return reviewRepository.findAllByReviewedTitleTitleIgnoreCaseContaining(title);
    }

    public List<Review> getReviewsByTitleId(String id, String type, String language, String location, Boolean spoilerAlert, Pageable pageable) {
        Predicate predicate = new BooleanBuilder()
                .and(ReviewByTitleId.get(id))
                .and(ReviewByType.get(type))
                .and(ReviewByLanguage.get(language))
                .and(ReviewByLocation.get(location))
                .and(ReviewBySpoilerAlert.get(spoilerAlert));

        return reviewRepository.findAll(predicate, pageable).getContent();
    }

    public Review getReviewById(Long id) { return reviewRepository.findById(id).get(); }

    public Review addReview(Review review, Long userId) {
        // Saving user
        User user = userRepository.findById(userId).get();
        review.setUser(user);
        // Saving review
        String titleId = review.getReviewedTitle().getTitleId();
        Review savedReview = reviewRepository.save(review);
        // Saving title
        Title title = titleRepository.findById(titleId).get();
        title.addReview(savedReview);
        titleRepository.save(title);
        String topic = getReviewTopic(review.getPlatform(), titleId);
        redisTemplate.convertAndSend(topic, savedReview).subscribe();
        return savedReview;
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review changeReport(Review review){
        Review reviewUpdated = review;
        reviewUpdated.setReported(true);
        return this.updateReview(reviewUpdated);
    }

    public void listenForReviews(String platform, String titleId, String callback) {
        redisTemplate
                .listenTo(ChannelTopic.of(getReviewTopic(platform, titleId)))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe((Review review) -> {
                    System.out.println("Letting " + platform + " subscribers of title "
                            + review.getReviewedTitle().getTitleId()
                            + " know that a new review has been written.\nRating: "
                            + review.getRating());
                    WebClient.builder()
                            .baseUrl(callback)
                            .build()
                            .get()
                            .retrieve()
                            .bodyToMono(Object.class)
                            .subscribe();
                });
        System.out.println("Platform " + platform + " subscribed to reviews from title " + titleId);
    }

    private String getReviewTopic(String platform, String titleId) {
        return "Review in platform " + platform + " for title with ID " + titleId;
    }

    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.getReviewsByUser(userId);
    }
}
