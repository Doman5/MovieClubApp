package com.domanski.movieclub.domain.rating;

import com.domanski.movieclub.domain.movie.Movie;
import com.domanski.movieclub.domain.movie.dto.MovieDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    Optional<Rating> findByUser_EmailAndMovie_Id(String email, Long movieId);
    void deleteRatingByMovie_Id(Long id);
}
