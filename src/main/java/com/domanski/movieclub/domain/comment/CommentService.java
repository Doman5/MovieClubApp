package com.domanski.movieclub.domain.comment;

import com.domanski.movieclub.domain.comment.dto.CommentDto;
import com.domanski.movieclub.domain.comment.dto.CommentToSaveDto;
import com.domanski.movieclub.domain.movie.MovieRepository;
import com.domanski.movieclub.domain.user.mapper.UserCredentialsDtoMapper;
import com.domanski.movieclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, MovieRepository movieRepository, UserCredentialsDtoMapper userCredentialsDtoMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<CommentDto> getAllCommentsByMovieId(long movieId) {
        return commentRepository.findAllByMovie_Id(movieId).stream()
                .map(CommentDtoMapper::map)
                .toList();
    }

    public void createComment(CommentToSaveDto commentToSave) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findByEmail(commentToSave.getUserName()).orElseThrow());
        comment.setMovie(movieRepository.findById(commentToSave.getMovieId()).orElseThrow());
        comment.setDate(LocalDateTime.now());
        comment.setContent(commentToSave.getContent());
        commentRepository.save(comment);
    }

    public void deleteCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
    }
}
