package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import ar.edu.unq.desapp.grupoC.backenddesappapi.converters.StringListConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Convert(converter = StringListConverter.class)
    private List<String> characters;

    public Actor(String name) {
        this.name = name;
    }

    public Actor() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
