package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.comment.CommentService;
import com.domanski.movieclub.domain.comment.dto.CommentDto;
import com.domanski.movieclub.domain.comment.dto.CommentToSaveDto;
import com.domanski.movieclub.domain.movieError.dto.MovieErrorDto;
import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import com.domanski.movieclub.domain.rating.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;
    private final CommentService commentService;

    public MovieController(MovieService movieService, RatingService ratingService, CommentService commentService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    @GetMapping("/film/{id}")
    public String showMovie(@PathVariable long id,
                            Model model,
                            Authentication authentication) {
        MovieDto movie = movieService.findMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movie);

        if(authentication != null) {
            String currentUserEmail = authentication.getName();
            Integer rating = ratingService.getUserRatingForMovie(currentUserEmail, id).orElse(0);
            model.addAttribute("userRating",rating);
            CommentToSaveDto comment = new CommentToSaveDto();
            model.addAttribute("comment", comment);
        }

        List<CommentDto> comments = commentService.getAllCommentsByMovieId(id);
        model.addAttribute("comments", comments);
        return "movie";
    }

    @GetMapping("/film")
    public String findMovieByName(@RequestParam String title, Model model) {
        List<MovieDto> movies = movieService.searchMovieByTitle(title);
        model.addAttribute("movies", movies);
        model.addAttribute("heading", "Wynik wyszukiwania dla " + title);
        return "movie-listing";
    }

    @GetMapping("/top-10")
    public String showTop10(Model model) {
        List<MovieDto> topMovie = movieService.findTopMovie(10);
        model.addAttribute("heading", "Filmowe Top 10 ");
        model.addAttribute("description", "Filmy najlepiej oceniane przez użytkowników");
        model.addAttribute("movies", topMovie);
        return "movie-listing";
    }



}
