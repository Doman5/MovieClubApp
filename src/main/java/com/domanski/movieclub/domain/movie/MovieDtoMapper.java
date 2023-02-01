package com.domanski.movieclub.domain.movie;

import com.domanski.movieclub.domain.genre.GenreRepository;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import com.domanski.movieclub.domain.rating.Rating;
import org.springframework.stereotype.Service;

@Service
public class MovieDtoMapper {
    private final GenreRepository genreRepository;

    public MovieDtoMapper(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public MovieDto map (Movie movie) {
        double avg = movie.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average()
                .orElse(0);

        return new MovieDto(movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getReleaseDate(),
                movie.getShortDescription(),
                movie.getDescription(),
                movie.getYoutubeTrailerId(),
                movie.getGenre().getName(),
                movie.isPromoted(),
                movie.getPoster(),
                avg,
                movie.getRatings().size());
    }

    public Movie map(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setOriginalTitle(movieDto.getOriginalTitle());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setShortDescription(movieDto.getShortDescription());
        movie.setDescription(movieDto.getDescription());
        movie.setYoutubeTrailerId(movieDto.getYoutubeTrailerId());
        movie.setGenre(genreRepository.findByNameIgnoreCase(movieDto.getGenre()).get());
        movie.setPromoted(movieDto.isPromoted());
        movie.setPoster(movieDto.getPoster());
        return movie;
    }
}
