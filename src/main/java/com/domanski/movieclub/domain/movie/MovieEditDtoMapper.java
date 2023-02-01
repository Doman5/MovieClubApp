package com.domanski.movieclub.domain.movie;

import com.domanski.movieclub.domain.movie.dto.MovieEditListDto;

class MovieEditDtoMapper {
    static MovieEditListDto map(Movie movie) {

        return new MovieEditListDto(movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getReleaseDate(),
                movie.getGenre().getName(),
                movie.isPromoted());
    }
}
