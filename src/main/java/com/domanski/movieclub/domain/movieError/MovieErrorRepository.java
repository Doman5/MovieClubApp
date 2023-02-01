package com.domanski.movieclub.domain.movieError;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MovieErrorRepository extends CrudRepository<MovieError, Long> {
    List<MovieError> findAll();
}
