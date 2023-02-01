package com.domanski.movieclub.domain.movieError;

import com.domanski.movieclub.domain.movieError.dto.MovieErrorDto;

public class MovieErrorDtoMapper {
    static public MovieError map(MovieErrorDto movieErrorDto) {
        MovieError movieError = new MovieError();
        movieError.setUserName(movieErrorDto.getUserName());
        movieError.setMovieId(movieErrorDto.getMovieId());
        movieError.setErrorPart(movieErrorDto.getErrorPart());
        movieError.setContent(movieErrorDto.getContent());
        return movieError;
    }

    static public MovieErrorDto map(MovieError movieError) {
        return new MovieErrorDto(movieError.getId(),
                movieError.getUserName(),
                movieError.getMovieId(),
                movieError.getErrorPart(),
                movieError.getContent());
    }
}
