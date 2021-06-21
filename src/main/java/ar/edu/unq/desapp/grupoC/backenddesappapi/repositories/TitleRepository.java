package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TitleRepository extends PagingAndSortingRepository<Title, String>, QuerydslPredicateExecutor<Title> {

    List<Title> findAll();
    Page<Title> findAll(Predicate predicate, Pageable pageable);
    Title findByTitle(String title);
}
