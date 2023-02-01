package com.domanski.movieclub.domain.comment;

import com.domanski.movieclub.domain.comment.dto.CommentDto;

public class CommentDtoMapper {
    static CommentDto map(Comment comment) {
        return new CommentDto(comment.getId(),
                comment.getUser().getEmail(),
                comment.getDate(),
                comment.getContent());
    }
}
