package com.domanski.movieclub.domain.movieError;

import com.domanski.movieclub.domain.movieError.dto.MovieErrorDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieErrorService {
    private final MovieErrorRepository movieErrorRepository;

    public MovieErrorService(MovieErrorRepository movieErrorRepository) {
        this.movieErrorRepository = movieErrorRepository;
    }

    public void saveError(MovieErrorDto errorDto) {
        MovieError movieError = MovieErrorDtoMapper.map(errorDto);
        movieErrorRepository.save(movieError);
    }

    public List<MovieErrorDto> findAllErrors() {
        return movieErrorRepository.findAll().stream()
                .map(MovieErrorDtoMapper::map)
                .toList();
    }

    public Optional<MovieErrorDto> findErrorById(long id) {
        return movieErrorRepository.findById(id)
                .map(MovieErrorDtoMapper::map);
    }

    @Transactional
    public void deleteErrorById(long id) {
        movieErrorRepository.deleteById(id);
    }
}
