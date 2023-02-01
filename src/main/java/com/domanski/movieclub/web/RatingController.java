package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.rating.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {

    private final MovieService movieService;
    private final RatingService ratingService;

    public RatingController(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    @PostMapping("/ocen-film")
    public String addMovieRating(@RequestParam long movieId,
                                 @RequestParam int rating,
                                 @RequestHeader String referer,
                                 Authentication authentication) {
        String currentUserEmail = authentication.getName();
        ratingService.addOrUpdateRating(currentUserEmail,movieId,rating);
        return "redirect:" + referer;
    }
}
