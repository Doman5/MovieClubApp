package com.domanski.movieclub.domain.movie;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Override
    Optional<Movie> findById(Long aLong);
    Optional<Movie> findById(long id);
    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByGenre_NameIgnoreCase(String name);
    List<Movie> findAllByOrderByTitleAsc();
    List<Movie> findAllByGenre_Id(long id);
    @Query("select m from Movie m join m.ratings r group by m order by avg(r.rating) DESC")
    List<Movie> findTopRatingsMovie(Pageable page);
    @Query("select m from Movie m where upper(m.title) like Upper(concat('%',:title,'%')) or Upper(m.originalTitle) like Upper(concat('%', :title,'%'))")
    List<Movie> findMovieByTitle(@Param("title") String title);



}
