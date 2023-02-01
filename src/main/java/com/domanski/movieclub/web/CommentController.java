package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.comment.CommentService;
import com.domanski.movieclub.domain.comment.dto.CommentToSaveDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/komentarz")
    public String createComment(CommentToSaveDto comment, @RequestHeader(HttpHeaders.REFERER) String header) {
        commentService.createComment(comment);
        return "redirect:"+ header;
    }

    @GetMapping("/komentarz/usun/{id}")
    public String deleteComment(@PathVariable long id, @RequestHeader(HttpHeaders.REFERER) String header) {
        commentService.deleteCommentById(id);
        return "redirect:"+ header;
    }
}
