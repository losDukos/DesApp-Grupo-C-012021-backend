package ar.edu.unq.desapp.grupoC.backenddesappapi.integration;

import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.TitleBuilder;
import ar.edu.unq.desapp.grupoC.backenddesappapi.factory.ControllerTestFactory;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Actor;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class TitleIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void no_titles_are_brought_if_there_arent_any() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        mvc.perform(get("/title")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        titleRepository.save(new TitleBuilder().build());

        mvc.perform(get("/title")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void the_first_page_of_titles_is_brought_with_size_1() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().build());
        titleRepository.save(new TitleBuilder().build());

        mvc.perform(get("/title?page=0&size=1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void only_titles_with_a_rating_of_4_or_more_are_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        titleRepository.save(new TitleBuilder().withRating(3.0).build());

        mvc.perform(get("/title?page=0&size=1&minRating=4.0")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_with_a_min_rating_of_4_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withRating(5.0).build());

        mvc.perform(get("/title?page=0&size=1&minRating=4.0")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())))
                .andExpect(jsonPath("$[0].averageRating", comparesEqualTo(5.0)));
    }

    @Test
    void only_titles_with_a_rating_of_4_or_less_are_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        titleRepository.save(new TitleBuilder().withRating(5.0).build());

        mvc.perform(get("/title?page=0&size=1&maxRating=4.0")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_with_a_max_rating_of_4_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withRating(4.0).build());

        mvc.perform(get("/title?page=0&size=1&maxRating=4.0")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())))
                .andExpect(jsonPath("$[0].averageRating", comparesEqualTo(4.0)));
    }

    @Test
    void no_action_titles_are_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        titleRepository.save(new TitleBuilder().withRating(4.0).build());

        mvc.perform(get("/title?page=0&size=1&genres=[\"action\"]")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void an_action_title_is_brought_by_action() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withGenres(Collections.singletonList("action")).build());

        mvc.perform(get("/title?page=0&size=1&genres=action")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void an_action_comedy_title_is_brought_by_action() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withGenres(Arrays.asList("action", "comedy")).build());

        mvc.perform(get("/title?page=0&size=1&genres=action")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void an_action_comedy_title_is_brought_by_action_comedy() throws Exception {
        Title title = titleRepository.save(new TitleBuilder().withGenres(Arrays.asList("action", "comedy")).build());
        String token = ControllerTestFactory.getUserToken(mvc);
        mvc.perform(get("/title?page=0&size=1&genres=action,comedy")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void no_titles_from_the_nineties_are_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        titleRepository.save(new TitleBuilder().withYear(1980).build());

        mvc.perform(get("/title?page=0&size=1&fromYear=1990&toYear=1999")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_from_the_nineties_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withYear(1995).build());

        mvc.perform(get("/title?page=0&size=1&fromYear=1990&toYear=1999")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void a_title_after_1995_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withYear(2020).build());

        mvc.perform(get("/title?page=0&size=1&fromYear=1995")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void a_title_before_1990_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Title title = titleRepository.save(new TitleBuilder().withYear(1880).build());

        mvc.perform(get("/title?page=0&size=1&toYear=1990")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void no_titles_from_bruce_willis_are_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Actor actor = new Actor("Marlon Brando");
        titleRepository.save(new TitleBuilder().withActor(actor).build());

        mvc.perform(get("/title?page=0&size=1&actor=Bruce Willis")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_from_bruce_willis_is_brought() throws Exception {
        String token = ControllerTestFactory.getUserToken(mvc);
        Actor actor = new Actor("Bruce Willis");
        Title title = titleRepository.save(new TitleBuilder().withActor(actor).build());

        mvc.perform(get("/title?page=0&size=1&actor=Bruce Willis")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void no_well_reviewed_titles_are_brought() throws Exception {
        titleRepository.save(new TitleBuilder().build());

        mvc.perform(get("/title?page=0&size=1&minReviews=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void a_title_with_two_reviews_is_brought() throws Exception {
        Title title = titleRepository.save(new TitleBuilder().withMixedReviews().build());

        mvc.perform(get("/title?page=0&size=1&minReviews=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }

    @Test
    void a_title_with_two_reviews_where_bruce_willis_acted_is_brought() throws Exception {
        Actor bruceWillis = new Actor("Bruce Willis");
        Title title = titleRepository.save(new TitleBuilder().withMixedReviews().withActor(bruceWillis).build());

        mvc.perform(get("/title?page=0&size=1&minReviews=2&actor=Bruce Willis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", comparesEqualTo(title.getTitleId())));
    }
}
