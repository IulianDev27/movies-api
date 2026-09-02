package com.movies.api.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MovieRepository
 * JPA Query Methods:
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByTitleContainingIgnoreCase(String title);

    List<MovieEntity> findByGenres_NameContainingIgnoreCase(String genre);
}
