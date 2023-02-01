package com.domanski.movieclub.domain.movie;

import com.domanski.movieclub.domain.genre.Genre;
import com.domanski.movieclub.domain.genre.GenreRepository;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import com.domanski.movieclub.domain.movie.dto.MovieEditListDto;
import com.domanski.movieclub.domain.movie.dto.MovieSaveDto;
import com.domanski.movieclub.domain.rating.RatingRepository;
import com.domanski.movieclub.storage.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;
    private final MovieDtoMapper movieDtoMapper;
    private final RatingRepository ratingRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, FileStorageService fileStorageService, MovieDtoMapper movieDtoMapper, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.fileStorageService = fileStorageService;
        this.movieDtoMapper = movieDtoMapper;
        this.ratingRepository = ratingRepository;
    }

    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue()
                .stream().map(movieDtoMapper::map)
                .toList();
    }

    public Optional<MovieDto> findMovieById(long id) {
        return movieRepository.findById(id)
                .map(movieDtoMapper::map);
    }

    public List<MovieDto> findMoviesByGenreName(String genre) {
        return movieRepository.findAllByGenre_NameIgnoreCase(genre)
                .stream().map(movieDtoMapper::map)
                .toList();
    }
    public void addMovie(MovieSaveDto movieToSave) {
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOriginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseDate(movieToSave.getReleaseDate());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if(movieToSave.getPoster() != null) {
            String poster = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(poster);
        }
        movieRepository.save(movie);
    }

    public List<MovieDto> findTopMovie(int size) {
        Pageable page = Pageable.ofSize(size);
        return movieRepository.findTopRatingsMovie(page).stream()
                .map(movieDtoMapper::map)
                .toList();
    }

    public List<MovieEditListDto> findAllMoviesToEdit() {
        return movieRepository.findAllByOrderByTitleAsc().stream()
                .map(MovieEditDtoMapper::map)
                .toList();
    }

    public void editMovie(MovieDto movieDto) {
        Movie movie = movieDtoMapper.map(movieDto);
        movieRepository.save(movie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
        ratingRepository.deleteRatingByMovie_Id(id);
    }

    public List<MovieDto> searchMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title).stream().
                map(movieDtoMapper::map).toList();
    }

    public Page<MovieDto> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MovieDto> movies = movieRepository.findAllByPromotedIsTrue().stream()
                .map(movieDtoMapper::map)
                .toList();
        List<MovieDto> list;

        if (movies.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, movies.size());
            list = movies.subList(startItem, toIndex);
        }

        Page<MovieDto> moviePage
                = new PageImpl<MovieDto>(list, PageRequest.of(currentPage, pageSize), movies.size());

        return moviePage;
    }
}
