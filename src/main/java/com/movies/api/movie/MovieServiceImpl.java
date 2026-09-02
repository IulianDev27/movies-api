package com.movies.api.movie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.movies.api.actor.ActorEntity;
import com.movies.api.actor.ActorRepository;
import com.movies.api.genre.GenreEntity;
import com.movies.api.genre.GenreRepository;
import com.movies.api.movie.dtos.MovieDTORequest;
import com.movies.api.movie.dtos.MovieDTOResponse;
import com.movies.api.movie.exceptions.MovieException;
import com.movies.api.movie.exceptions.MovieExceptionNotFound;
import com.movies.api.year.YearEntity;
import com.movies.api.year.YearRepository;

@Service
public class MovieServiceImpl implements InterfaceMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final YearRepository yearRepository;
    private final MovieMapper mapper;

    public MovieServiceImpl(MovieRepository movieRepository,
            GenreRepository genreRepository,
            ActorRepository actorRepository,
            YearRepository yearRepository,
            MovieMapper mapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
        this.yearRepository = yearRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MovieDTOResponse> findAll() {
        return movieRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MovieDTOResponse findById(Long id) {
        return mapper.toResponse(findEntityById(id));
    }

    @Override
    public MovieDTOResponse create(MovieDTORequest request) {
        MovieEntity movie = new MovieEntity();
        applyRequest(movie, request);
        return mapper.toResponse(movieRepository.save(movie));
    }

    @Override
    public MovieDTOResponse update(Long id, MovieDTORequest request) {
        MovieEntity movie = findEntityById(id);
        applyRequest(movie, request);
        return mapper.toResponse(movieRepository.save(movie));
    }

    @Override
    public void delete(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieExceptionNotFound("Pelicula no encontrada: id=" + id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDTOResponse> search(String title, String genre) {
        List<MovieEntity> found;

        if (title != null && !title.isBlank()) {
            found = movieRepository.findByTitleContainingIgnoreCase(title);
        } else if (genre != null && !genre.isBlank()) {
            found = movieRepository.findByGenres_NameContainingIgnoreCase(genre);
        } else {
            throw new MovieException("Indica un titulo o un genero para buscar");
        }

        return found.stream()
                .map(mapper::toResponse)
                .toList();
    }

    private MovieEntity findEntityById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieExceptionNotFound("Pelicula no encontrada: id=" + id));
    }

    private void applyRequest(MovieEntity movie, MovieDTORequest request) {
        movie.setTitle(request.title());
        movie.setSynopsis(request.synopsis());
        movie.setDuration(request.duration());
        movie.setYear(resolveYear(request.yearId()));
        movie.setGenres(resolveGenres(request.genreIds()));
        movie.setActors(resolveActors(request.actorIds()));
    }

    private YearEntity resolveYear(Long yearId) {
        if (yearId == null) {
            return null;
        }
        return yearRepository.findById(yearId)
                .orElseThrow(() -> new MovieException("Anyo no encontrado: id=" + yearId));
    }

    private Set<GenreEntity> resolveGenres(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        List<GenreEntity> found = genreRepository.findAllById(ids);
        if (found.size() != ids.size()) {
            throw new MovieException("Alguno de los generos indicados no existe");
        }
        return new HashSet<>(found);
    }

    private Set<ActorEntity> resolveActors(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        List<ActorEntity> found = actorRepository.findAllById(ids);
        if (found.size() != ids.size()) {
            throw new MovieException("Alguno de los actores indicados no existe");
        }
        return new HashSet<>(found);
    }
}
