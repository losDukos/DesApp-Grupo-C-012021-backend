package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Title, String> {

    List<Title> findAll();
    User findByName(String name);
}
