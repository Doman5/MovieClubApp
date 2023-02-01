package com.domanski.movieclub.domain.comment.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String userName;
    private LocalDateTime date;
    private String content;


    public CommentDto() {
    }

    public CommentDto(Long id, String userName, LocalDateTime date, String content) {
        this.id = id;
        this.userName = userName;
        this.date = date;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
