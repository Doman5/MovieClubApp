package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.movieError.MovieErrorService;
import com.domanski.movieclub.domain.movieError.dto.MovieErrorDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieErrorController {
    private final MovieErrorService movieErrorService;

    public MovieErrorController(MovieErrorService movieErrorService) {
        this.movieErrorService = movieErrorService;
    }

    @GetMapping("/film/error/{movieId}")
    public String getErrorForm(@PathVariable long movieId, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        MovieErrorDto movieError = new MovieErrorDto();
        movieError.setMovieId(movieId);
        movieError.setUserName(userName);
        model.addAttribute("movieError", movieError);
        return "movie-error-form";
    }

    @PostMapping("/film/error")
    public String addMovieError(MovieErrorDto movieError) {
        movieErrorService.saveError(movieError);
        return "movie-error-thanks";
    }
}
