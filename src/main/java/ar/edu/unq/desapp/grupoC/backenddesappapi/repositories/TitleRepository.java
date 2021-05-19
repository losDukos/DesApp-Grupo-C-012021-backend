package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TitleRepository extends PagingAndSortingRepository<Title, String>, JpaSpecificationExecutor<Title> {

    List<Title> findAll();
    Page<Title> findAll(Specification<Title> specs, Pageable pageable);
}
