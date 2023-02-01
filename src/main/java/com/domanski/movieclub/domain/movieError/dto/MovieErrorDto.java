package com.domanski.movieclub.domain.movieError.dto;

public class MovieErrorDto {
    private Long id;
    private String userName;
    private Long movieId;
    private String errorPart;
    private String content;

    public MovieErrorDto() {
    }

    public MovieErrorDto(Long id, String userName, Long movieId, String errorPart, String content) {
        this.id = id;
        this.userName = userName;
        this.movieId = movieId;
        this.errorPart = errorPart;
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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getErrorPart() {
        return errorPart;
    }

    public void setErrorPart(String errorPart) {
        this.errorPart = errorPart;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
