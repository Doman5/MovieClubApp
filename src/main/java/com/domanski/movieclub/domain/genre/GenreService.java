package com.domanski.movieclub.domain.genre;

import com.domanski.movieclub.domain.genre.dto.GenreDto;
import com.domanski.movieclub.domain.movie.Movie;
import com.domanski.movieclub.domain.movie.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final GenreDtoMapper genreDtoMapper;

    public GenreService(GenreRepository genreRepository, MovieRepository movieRepository, GenreDtoMapper genreDtoMapper) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.genreDtoMapper = genreDtoMapper;
    }

    public Optional<GenreDto> findGenreByName(String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .map(genreDtoMapper::map);
    }

    public Optional<GenreDto> findGenreById(long id) {
        return genreRepository.findById(id)
                .map(genreDtoMapper::map);
    }

    public List<GenreDto> findAllGenre() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(genreDtoMapper::map)
                .toList();
    }

    public void addGenre(GenreDto genre) {
        Genre genreToSave = new Genre();
        genreToSave.setName(genre.getName());
        genreToSave.setDescription(genre.getDescription());
        genreRepository.save(genreToSave);
    }

    @Transactional
    public void editGenre(GenreDto genreDto) {
        Genre genre = genreDtoMapper.map(genreDto);
        genreRepository.save(genre);
    }

    @Transactional
    public void deleteGenre(long id) {

        if(genreRepository.findByNameIgnoreCase("Brak").isEmpty()) {
            Genre genre = new Genre("Brak","Brak kategorii");
            genreRepository.save(genre);
        }
        Genre nullGenre = genreRepository.findByNameIgnoreCase("Brak").orElseThrow();

        for (Movie movie : movieRepository.findAllByGenre_Id(id)) {
            movie.setGenre(nullGenre);
            movieRepository.save(movie);
        }

        genreRepository.deleteById(id);

    }
}
