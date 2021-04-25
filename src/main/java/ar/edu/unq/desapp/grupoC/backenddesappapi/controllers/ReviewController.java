package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/title/{title}")
    public @ResponseBody List<Review> getReviewsByTitle(@PathVariable String title) {
        return reviewService.getReviewsByTitle(title);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody List<Review> getReviewsByTitleId(@PathVariable String id) {
        return reviewService.getReviewsByTitleId(id);
    }

}
