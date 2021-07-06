package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.dto.ListenForReviewsRequestBody;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        UserDetail userDetails = (UserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return reviewService.addReview(review, userDetails.getId());
    }

    @PostMapping(path = "/{idReview}")
    public Appraisal rateReview(@RequestBody UserAppraisal userAppraisal, @PathVariable Long idReview) {
        UserDetail userDetails = (UserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Review review = reviewService.getReviewById(idReview);
        User user = userService.userById(userDetails.getId());

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

    @PostMapping(path = "/listen")
    public void listenForReviews(@RequestBody ListenForReviewsRequestBody request) {
        reviewService.listenForReviews(request.getPlatform(), request.getTitle(), request.getCallback());
    }
}
