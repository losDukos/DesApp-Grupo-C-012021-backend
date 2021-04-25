package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoC.backenddesappapi.DataLoader;
import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.ReviewBuilder;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewControllerTests {
    @MockBean
    private DataLoader dataLoader;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private Title title;

    private Review review;

    @BeforeEach
    void setUp() {
        title = titleRepository.findById("tt0129167").get();
    }

    @Test
    void a_title_has_no_reviews() throws Exception {
        mvc.perform(get("/review/title/" + title.getTitle()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_review_is_retrieved_by_exact_title() throws Exception {
        review = ReviewBuilder.buildPublicReview(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/title/" + title.getTitle()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }

    @Test
    void a_review_is_retrieved_by_part_of_the_title() throws Exception {
        review = ReviewBuilder.buildPublicReview(title);
        reviewRepository.save(review);

        mvc.perform(get("/review/title/" + title.getTitle().substring(0, 2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", comparesEqualTo(Math.toIntExact(review.getId()))));
    }
}
