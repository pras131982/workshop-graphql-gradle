package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MovieEntity addMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieEntity findMovieById(Integer movieId) {
        return movieRepository.getOne(movieId);
    }

    @Override
    public List<MovieEntity> listMovies() {
        return movieRepository.findAll();
    }
}
