package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/title/{title}")
    public @ResponseBody List<Review> getReviewsByTitle(@PathVariable String title) {
        return reviewService.getReviewsByTitle(title);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody List<Review> getReviewsByTitleId(
            @PathVariable String id, @RequestParam(required = false) Boolean spoilerAlert,
            @RequestParam(required = false) String type, @RequestParam(required = false) String language,
            @RequestParam(required = false) String location, Pageable pageable) {

        return reviewService.getReviewsByTitleId(id, type, language, location, spoilerAlert, pageable);
    }

    @GetMapping("/id/unreported/{id}")
    public @ResponseBody List<Review> getReviewsUnreporteredsByTitleId(
            @PathVariable String id, @RequestParam(required = false) Boolean spoilerAlert, @RequestParam(required = false) String type,
            @RequestParam(required = false) String language, @RequestParam(required = false) String location,
            Pageable pageable
    ){
        Specification<Review> specs = Specification.where(new ReviewByTitleId(id))
                .and(new ReviewByType(type))
                .and(new ReviewByLanguage(language))
                .and(new ReviewByLocation(location))
                .and(new ReviewBySpoilerAlert(spoilerAlert));

        List<Review> allReviews = reviewService.getReviewsByTitleId(specs, pageable);
        return allReviews.stream()
                .filter(r -> !r.getIsReported())
                .collect(Collectors.toList());
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @PostMapping(path = "/{idReview}")
    public Appraisal rateReview(@RequestBody UserAppraisal userAppraisal, @PathVariable Long idReview) {
        Review review = reviewService.getReviewById(idReview);
        User user = userService.userById(userAppraisal.getUserId());

        if (userAppraisal.getPositive()) {
            review.appraisePositivelyBy(user);
        } else {
            review.appraiseNegativelyBy(user);
        }
        Review updatedReview = reviewService.updateReview(review);

        return updatedReview.getUserAppraisal(user).get();
    }
    @PostMapping(path = "/report/{idReview}")
    public Review reportReview( @PathVariable Long idReview){
        Review review = reviewService.getReviewById(idReview);
        return reviewService.changeReport(review);
    }
}
