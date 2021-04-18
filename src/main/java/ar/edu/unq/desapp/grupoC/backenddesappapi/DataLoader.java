package ar.edu.unq.desapp.grupoC.backenddesappapi;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Episode;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedTitlesTable();
    }

    private void seedTitlesTable() {
        List<Title> titles = titleRepository.findAll();
        if (!titles.isEmpty()) {
            return;
        }
        Title elGiganteDeHierro = new Title("tt0129167", 30, "El gigante de hierro", "MX", "",
                Collections.singletonList("imdbDisplay"), Collections.emptyList(), false, "movie",	"The Iron Giant",
                "The Iron Giant",	false, 1999,	null,	86,
                Arrays.asList("Action", "Adventure", "Animation"), 8.0,	174152);
        titleRepository.save(elGiganteDeHierro);

        Title theRoadToElDorado = new Title("tt0138749", 19, "The Road to El Dorado", "IN", "en",
                Collections.singletonList("imdbDisplay"), Collections.emptyList(), false, "movie",	"The Road to El Dorado",
                "The Road to El Dorado",	false, 2000,	null,	89,
                Arrays.asList("Adventure", "Animation", "Comedy"), 6.9,	85803);
        titleRepository.save(theRoadToElDorado);

        Title alDiabloConElDiablo = new Title("tt0061391", 5, "Al diablo con el diablo", "ES", "",
                Collections.singletonList("dvd"), Collections.emptyList(), false, "movie",	"Bedazzled",
                "Bedazzled",	false, 1967,	null,	103,
                Arrays.asList("Comedy", "Fantasy", "Romance"), 6.9,	8427);
        titleRepository.save(alDiabloConElDiablo);

        Title seinfeld = new Title("tt0098904", 10, "Seinfeld", "MX", "",
                Collections.emptyList(), Collections.emptyList(), false, "tvSeries",	"Seinfeld",
                "Seinfeld",	false, 1989,	1998,	22,
                Collections.singletonList("Comedy"), 8.8,	258651);
        titleRepository.save(seinfeld);

        Title theFreshPrince = new Title("tt0098800", 18, "The Fresh Prince of Bel-Air", "AU", "",
                Collections.singletonList("imdbDisplay"), Collections.emptyList(), false, "tvSeries",	"The Fresh Prince of Bel-Air",
                "The Fresh Prince of Bel-Air",	false, 1990,	1996,	22,
                Collections.singletonList("Comedy"), 7.9,	122831);
        titleRepository.save(theFreshPrince);

        Title springfield = new Episode("tt0701040", "tvEpisode", "$pringfield (Or, How I Learned to Stop Worrying and Love Legalized Gambling)",
                "$pringfield (Or, How I Learned to Stop Worrying and Love Legalized Gambling)", false, 1993,
                null, 30, Arrays.asList("Animation", "Comedy"), 8.6, 2509, "tt0096697",
                5, 10);
        titleRepository.save(springfield);

        Title theSecretSnakeClub = new Episode("tt0701040", "tvEpisode", "The Secret Snake Club",
                "The Secret Snake Club", false, 2005,
                null, 21, Arrays.asList("Adventure", "Animation", "Comedy"), 7.9, 48,
                "tt0292800", 4, 1);
        titleRepository.save(theSecretSnakeClub);
    }
}
