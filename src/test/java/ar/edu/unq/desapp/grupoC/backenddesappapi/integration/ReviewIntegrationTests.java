package ar.edu.unq.desapp.grupoC.backenddesappapi.integration;

import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.ReviewBuilder;
import ar.edu.unq.desapp.grupoC.backenddesappapi.factory.ControllerTestFactory;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ReviewIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private ReactiveRedisOperations<String, Review> mockRedisTemplate;

    private Title title;
    private Review review;

    @BeforeEach
    void setup() {
        title = titleRepository.save(new Title("aTitleId", "Title"));
        Mono<Long> mockMono = Mockito.mock((Mono.class));
        Mockito.when(mockRedisTemplate.convertAndSend(anyString(), any(Review.class))).thenReturn(mockMono);
    }

    // GET: Reviews by Title title

    @Test
    void a_title_has_no_reviews() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        mvc.perform(get("/review/title/" + title.getTitle())
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_review_is_retrieved_by_exact_title() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withPremium(false).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/title/" + title.getTitle())
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void a_review_is_retrieved_by_part_of_the_title() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withPremium(false).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/title/" + title.getTitle().substring(0, 2))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    // GET: Reviews by Title ID

    @Test
    void a_review_is_retrieved_by_title_id() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        ReviewBuilder builder = new ReviewBuilder().withPremium(false);
        Title anotherTitle = titleRepository.save(new Title("anotherTitleId", "Another Title"));
        Review anotherReview = builder.build(anotherTitle);
        reviewRepository.save(anotherReview);

        review = builder.build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId())
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

        // Filter Reviews

    @Test
    void spoilery_reviews_are_retrieved_by_title_id() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withSpoilerAlert(true).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?spoilerAlert=true")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void spoilery_reviews_are_excluded_from_the_search() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withSpoilerAlert(true).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?spoilerAlert=false")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void argentinian_reviews_are_retrieved_by_title_id() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withLocation("Argentina").build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?location=Argentina")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void argentinian_reviews_are_excluded() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withLocation("Argentina").build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?location=United States")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void english_reviews_are_retrieved_by_title_id() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withLanguage("English").build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?language=English")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void english_reviews_are_excluded() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withLocation("English").build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?language=Spanish")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void premium_reviews_are_retrieved_by_title_id() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withPremium(true).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?type=critic")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void premium_reviews_are_excluded() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().withPremium(true).build(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?type=public")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

        // Sort Reviews

    @Test
    void reviews_are_sorted_by_rating_asc() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Review lastReview = new ReviewBuilder().withRating(5.0).build(title);
        reviewRepository.save(lastReview);

        Review firstReview =  new ReviewBuilder().withRating(3.0).build(title);
        reviewRepository.save(firstReview);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?sort=rating,asc")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(firstReview.getId()))))
                .andExpect(jsonPath("$[1].id", comparesEqualTo(Math.toIntExact(lastReview.getId()))));
    }

    @Test
    void reviews_are_sorted_by_rating_desc() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Review lastReview = new ReviewBuilder().withRating(3.0).build(title);
        reviewRepository.save(lastReview);

        Review firstReview =  new ReviewBuilder().withRating(5.0).build(title);
        reviewRepository.save(firstReview);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?sort=rating,desc")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(firstReview.getId()))))
                .andExpect(jsonPath("$[1].id", comparesEqualTo(Math.toIntExact(lastReview.getId()))));
    }

    @Test
    void reviews_are_sorted_by_date_asc() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Review lastReview = new ReviewBuilder().withDate(new SimpleDateFormat("dd-MM-yyyy").parse("06-06-2022")).build(title);
        reviewRepository.save(lastReview);

        Review firstReview =  new ReviewBuilder().withDate(new SimpleDateFormat("dd-MM-yyyy").parse("06-06-2020")).build(title);
        reviewRepository.save(firstReview);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?sort=date,asc")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(firstReview.getId()))))
                .andExpect(jsonPath("$[1].id", comparesEqualTo(Math.toIntExact(lastReview.getId()))));
    }

    @Test
    void reviews_are_sorted_by_date_desc() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Review lastReview = new ReviewBuilder().withDate(new SimpleDateFormat("dd-MM-yyyy").parse("06-06-2020")).build(title);
        reviewRepository.save(lastReview);

        Review firstReview =  new ReviewBuilder().withDate(new SimpleDateFormat("dd-MM-yyyy").parse("06-06-2022")).build(title);
        reviewRepository.save(firstReview);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?sort=date,desc")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(firstReview.getId()))))
                .andExpect(jsonPath("$[1].id", comparesEqualTo(Math.toIntExact(lastReview.getId()))));
    }

        // Paginate Reviews

    @Test
    void reviews_are_paginated() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Review firstReview = new ReviewBuilder().build(title);
        reviewRepository.save(firstReview);

        Review lastReview =  new ReviewBuilder().build(title);
        reviewRepository.save(lastReview);

        mvc.perform(get("/review/id/" + title.getTitleId() + "?page=0&size=1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(firstReview.getId()))));

        mvc.perform(get("/review/id/" + title.getTitleId() + "?page=1&size=1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(lastReview.getId()))));

        mvc.perform(get("/review/id/" + title.getTitleId() + "?page=0&size=2")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    // POST: Add Review

    @Test
    void a_public_review_is_created() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        String json = "{\"reviewedTitle\": {\"titleId\": \"" + title.getTitleId() + "\"}}";
        mvc.perform(post("/review").contentType(MediaType.APPLICATION_JSON).content(json)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reviewedTitle.titleId", equalTo(title.getTitleId())))
        .andExpect(jsonPath("$.premium", is(false)));
    }

    @Test
    void a_premium_review_is_created() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        String json = "{\"premium\": true, \"reviewedTitle\": {\"titleId\": \"" + title.getTitleId() + "\"}}";
        mvc.perform(post("/review").contentType(MediaType.APPLICATION_JSON).content(json)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewedTitle.titleId", equalTo(title.getTitleId())))
                .andExpect(jsonPath("$.premium", is(true)));
    }

    // POST: Rate Review

    @Test
    void a_review_is_rated_possitively() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().build(title);
        Review savedReview = reviewRepository.save(review);
        User savedUser = userRepository.save(new User());

        String json = "{\"positive\": true, \"userId\": " + savedUser.getId() + "}";
        mvc.perform(post("/review/" + savedReview.getId()).contentType(MediaType.APPLICATION_JSON).content(json)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.user.id", equalTo(savedUser.getId().intValue())))
        .andExpect(jsonPath("$.positive", is(true)));
    }

    @Test
    void a_review_is_rated_negatively() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().build(title);
        Review savedReview = reviewRepository.save(review);
        User savedUser = userRepository.save(new User());

        String json = "{\"positive\": false, \"userId\": " + savedUser.getId() + "}";
        mvc.perform(post("/review/" + savedReview.getId()).contentType(MediaType.APPLICATION_JSON).content(json)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id", equalTo(savedUser.getId().intValue())))
                .andExpect(jsonPath("$.positive", is(false)));
    }

    @Test
    void a_review_is_edited_and_the_amount_of_rates_stays_the_same() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        review = new ReviewBuilder().build(title);
        Review savedReview = reviewRepository.save(review);
        User savedUser = userRepository.save(new User());

        String negativeJson = "{\"positive\": false, \"userId\": " + savedUser.getId() + "}";
        mvc.perform(post("/review/" + savedReview.getId()).contentType(MediaType.APPLICATION_JSON).content(negativeJson)
                .header("Authorization", "Bearer " + token));

        String positiveJson = "{\"positive\": true, \"userId\": " + savedUser.getId() + "}";
        mvc.perform(post("/review/" + savedReview.getId()).contentType(MediaType.APPLICATION_JSON).content(positiveJson)
                .header("Authorization", "Bearer " + token));

        Review retrievedReview = reviewRepository.findById(savedReview.getId()).get();

        assertEquals(retrievedReview.getLikes(), 1);
        assertEquals(retrievedReview.getDislikes(), 0);
        assertEquals(retrievedReview.getAppraisals().size(), 1);
    }
}
