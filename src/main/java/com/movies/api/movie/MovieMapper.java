package com.movies.api.movie;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.movies.api.actor.ActorEntity;
import com.movies.api.genre.GenreEntity;
import com.movies.api.movie.dtos.MovieDTOResponse;

@Component
public class MovieMapper {

    public MovieDTOResponse toResponse(MovieEntity movie) {
        return new MovieDTOResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getSynopsis(),
                movie.getDuration(),
                movie.getYear() == null ? null : movie.getYear().getValue(),
                genreNames(movie.getGenres()),
                actorNames(movie.getActors()));
    }

    private Set<String> genreNames(Set<GenreEntity> genres) {
        if (genres == null) {
            return Set.of();
        }
        return genres.stream()
                .map(GenreEntity::getName)
                .collect(Collectors.toSet());
    }

    private Set<String> actorNames(Set<ActorEntity> actors) {
        if (actors == null) {
            return Set.of();
        }
        return actors.stream()
                .map(ActorEntity::getName)
                .collect(Collectors.toSet());
    }
}
