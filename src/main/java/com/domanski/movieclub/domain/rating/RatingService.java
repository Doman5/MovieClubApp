package com.domanski.movieclub.domain.rating;

import com.domanski.movieclub.domain.movie.Movie;
import com.domanski.movieclub.domain.movie.MovieRepository;
import com.domanski.movieclub.domain.user.User;
import com.domanski.movieclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingService(UserRepository userRepository, RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    public void addOrUpdateRating(String email, long movieId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndMovie_Id(email,movieId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(email).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setMovie(movie);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }

    public Optional<Integer> getUserRatingForMovie(String email, long movieId) {
        return ratingRepository.findByUser_EmailAndMovie_Id(email,movieId)
                .map(Rating::getRating);
    }
}
