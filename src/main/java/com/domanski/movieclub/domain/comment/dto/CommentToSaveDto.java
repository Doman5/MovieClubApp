package com.domanski.movieclub.domain.comment.dto;

import com.domanski.movieclub.domain.movie.Movie;
import com.domanski.movieclub.domain.user.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class CommentToSaveDto {
    private String userName;
    private Long movieId;
    private String content;

    public CommentToSaveDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
