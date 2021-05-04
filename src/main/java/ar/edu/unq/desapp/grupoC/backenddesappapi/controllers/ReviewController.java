package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Appraisal;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.UserAppraisal;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Controller
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

    @PostMapping(path = "/review", consumes = "application/json", produces = "application/json")
    public void addReview(@RequestBody String json) {
        try{
            //Jakson
            ObjectMapper objectMapper = new ObjectMapper();
            Review review = objectMapper.readValue(json, Review.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/rateReview/{idReview}", consumes = "application/json", produces = "application/json")
    public void rateReview(@RequestBody UserAppraisal userAppraisal, @PathVariable Long idReview) {

        Review review = reviewService.getReviewById(idReview);
        User user = userService.userById(userAppraisal.getUserId());

        review.setAppraisalBy(user, userAppraisal.getPositive());
    }
}
