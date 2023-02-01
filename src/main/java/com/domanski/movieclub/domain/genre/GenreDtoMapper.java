package com.domanski.movieclub.domain.genre;

import com.domanski.movieclub.domain.genre.dto.GenreDto;
import org.springframework.stereotype.Service;

@Service
class GenreDtoMapper {
    GenreDto map(Genre genre) {
        return new GenreDto(genre.getId(),
                genre.getName(),
                genre.getDescription());
    }

    Genre map(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        genre.setDescription(genreDto.getDescription());
        return genre;
    }


}
