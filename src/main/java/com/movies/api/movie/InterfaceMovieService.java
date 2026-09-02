package com.movies.api.movie;

import java.util.List;

import com.movies.api.movie.dtos.MovieDTORequest;
import com.movies.api.movie.dtos.MovieDTOResponse;

public interface InterfaceMovieService {

    List<MovieDTOResponse> findAll();

    MovieDTOResponse findById(Long id);

    MovieDTOResponse create(MovieDTORequest request);

    MovieDTOResponse update(Long id, MovieDTORequest request);

    void delete(Long id);

    List<MovieDTOResponse> search(String title, String genre);
}
