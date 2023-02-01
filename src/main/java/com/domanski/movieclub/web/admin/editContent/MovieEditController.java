package com.domanski.movieclub.web.admin.editContent;

import com.domanski.movieclub.domain.genre.GenreService;
import com.domanski.movieclub.domain.genre.dto.GenreDto;
import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import com.domanski.movieclub.domain.movie.dto.MovieEditListDto;
import com.domanski.movieclub.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieEditController {
    private final MovieService movieService;
    private final GenreService genreService;

    public MovieEditController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/admin/filmy")
    public String getMovieList(Model model) {
        List<MovieEditListDto> movies = movieService.findAllMoviesToEdit();
        model.addAttribute("movies", movies);
        return "admin/edit-movie-panel";
    }

    @GetMapping("/admin/filmy/{id}")
    public String getMovieToEdit(@PathVariable long id, Model model) {
        MovieDto movie = movieService.findMovieById(id).orElseThrow();
        List<GenreDto> allGenre = genreService.findAllGenre();
        model.addAttribute("movie",movie);
        model.addAttribute("genres", allGenre);
        return "admin/edit-movie";
    }

    @PostMapping("/admin/filmy")
    public String editMovie(MovieDto movie, RedirectAttributes redirectAttributes) {
        movieService.editMovie(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został edytowany".formatted(movie.getTitle()));
        return "redirect:/admin";
    }

    @GetMapping("/admin/filmy/usun/{id}")
    public String deleteMovie(@PathVariable long id, RedirectAttributes redirectAttributes) {
        MovieDto movie = movieService.findMovieById(id).orElseThrow();
        movieService.deleteMovie(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został usunięty".formatted(movie.getTitle()));
        return "redirect:/admin";
    }
}
