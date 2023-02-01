package com.domanski.movieclub.web.admin.movieErrors;

import com.domanski.movieclub.domain.genre.GenreService;
import com.domanski.movieclub.domain.genre.dto.GenreDto;
import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import com.domanski.movieclub.domain.movieError.MovieErrorService;
import com.domanski.movieclub.domain.movieError.dto.MovieErrorDto;
import com.domanski.movieclub.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class AdminErrorController {
    private final MovieErrorService movieErrorService;
    private final MovieService movieService;
    private final GenreService genreService;

    public AdminErrorController(MovieErrorService movieErrorService, MovieService movieService, GenreService genreService) {
        this.movieErrorService = movieErrorService;
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/admin/error")
    public String getErrors(Model model) {
        List<MovieErrorDto> allErrors = movieErrorService.findAllErrors();
        model.addAttribute("errors", allErrors);
        return "/admin/errors";
    }

    @GetMapping("/admin/error/{id}")
    public String repairMovieWithError(@PathVariable long id,Model model) {
        MovieErrorDto movieError = movieErrorService.findErrorById(id).orElseThrow();
        MovieDto movie = movieService.findMovieById(movieError.getMovieId()).orElseThrow();
        List<GenreDto> genres = genreService.findAllGenre();
        model.addAttribute("movie", movie);
        model.addAttribute("error", movieError);
        model.addAttribute("genres", genres);
        return "admin/repair-movie-with-error";
    }

    @PostMapping("/admin/error")
    public String saveRepairedMovie(MovieDto movie, @RequestParam long errorId, RedirectAttributes redirectAttributes) {
        movieService.editMovie(movie);
        movieErrorService.deleteErrorById(errorId);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s zosał edytowany".formatted(movie.getTitle())
        );
        return "redirect:/admin";
    }
}
