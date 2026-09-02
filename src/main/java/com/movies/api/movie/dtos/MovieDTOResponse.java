package com.movies.api.movie.dtos;

import java.util.Set;

public record MovieDTOResponse(
        Long id,
        String title,
        String synopsis,
        Integer duration,
        Integer year,
        Set<String> genres,
        Set<String> actors) {
}
