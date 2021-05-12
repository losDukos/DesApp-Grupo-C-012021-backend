package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody List<Review> getReviewsByTitleId(@PathVariable String id) {
        return reviewService.getReviewsByTitleId(id);
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
}
