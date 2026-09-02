package com.movies.api.movie.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MovieDTORequest(
        @NotBlank(message = "El titulo es obligatorio") String title,

        String synopsis,

        @Positive(message = "La duracion debe ser mayor que cero") Integer duration,

        Long yearId,

        Set<Long> genreIds,

        Set<Long> actorIds) {
}
