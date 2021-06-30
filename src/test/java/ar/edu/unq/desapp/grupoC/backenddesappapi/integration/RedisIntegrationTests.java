package ar.edu.unq.desapp.grupoC.backenddesappapi.integration;

import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.TitleBuilder;
import ar.edu.unq.desapp.grupoC.backenddesappapi.config.TestRedisConfiguration;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestRedisConfiguration.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RedisIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TitleRepository titleRepository;

    @Test
    void an_abridged_title_is_brought_from_redis_the_second_time() throws Exception {
        String aTestTitle = "a test title";
        when(titleRepository.findByTitle(aTestTitle)).thenReturn(new TitleBuilder().withTitle(aTestTitle).build());

        verify(titleRepository, Mockito.times(0)).findByTitle(aTestTitle);

        mvc.perform(get("/title/" + aTestTitle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", comparesEqualTo(aTestTitle)));

        verify(titleRepository, Mockito.times(1)).findByTitle(aTestTitle);

        mvc.perform(get("/title/" + aTestTitle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", comparesEqualTo(aTestTitle)));

        verify(titleRepository, Mockito.times(1)).findByTitle(aTestTitle);
    }
}
