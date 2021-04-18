package ar.edu.unq.desapp.grupoC.backenddesappapi;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
        // Codigo de seedeo
    }
}
