package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TitleRepository extends CrudRepository<Title, String> {

    List<Title> findAll();
}
