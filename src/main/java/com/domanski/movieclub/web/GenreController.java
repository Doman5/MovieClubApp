package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.genre.GenreService;
import com.domanski.movieclub.domain.genre.dto.GenreDto;
import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    public GenreController(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @GetMapping("/gatunek/{name}")
    public String getGenre(@PathVariable String name, Model model) {
        if(name.equals("Brak")) {
            return "error/404";
        }
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<MovieDto> movies = movieService.findMoviesByGenreName(name);
        model.addAttribute("heading", genre.getName());
        model.addAttribute("description", genre.getDescription());
        model.addAttribute("movies", movies);
        return "movie-listing";
    }

    @GetMapping("/gatunki-filmowe")
    public String getGenreList(Model model) {
        List<GenreDto> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres);
        return "genre-listing";
    }
}
