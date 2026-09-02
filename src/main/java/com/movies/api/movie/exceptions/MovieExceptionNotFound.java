package com.movies.api.movie.exceptions;

public class MovieExceptionNotFound extends MovieException {

    public MovieExceptionNotFound(String message) {
        super(message);
    }

    public MovieExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
