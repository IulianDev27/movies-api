package com.movies.api.movie;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.api.movie.dtos.MovieDTORequest;
import com.movies.api.movie.dtos.MovieDTOResponse;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final InterfaceMovieService movieService;

    public MovieController(InterfaceMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTOResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTOResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTOResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
        return ResponseEntity.ok(movieService.search(title, genre));
    }

    @PostMapping
    public ResponseEntity<MovieDTOResponse> create(@Valid @RequestBody MovieDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTOResponse> update(@PathVariable Long id,
            @Valid @RequestBody MovieDTORequest request) {
        return ResponseEntity.ok(movieService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
