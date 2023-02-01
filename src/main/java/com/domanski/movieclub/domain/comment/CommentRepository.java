package com.domanski.movieclub.domain.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByMovie_Id(long id);
}
