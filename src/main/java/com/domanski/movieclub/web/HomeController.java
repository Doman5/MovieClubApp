package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.movie.MovieService;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    public static final String NOTIFICATION_ATTRIBUTE = "";
    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping( "/")
    public String listMovies(
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {

        Page<MovieDto> moviePage = movieService.findPaginated(PageRequest.of(page - 1, size));

        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecan przez nasz zespół");
        model.addAttribute("moviePage", moviePage);

        int totalPages = moviePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "movie-listing";
    }
}
