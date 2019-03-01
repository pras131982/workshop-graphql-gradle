package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.MovieEntity;

import java.util.List;


public interface MovieService {

    MovieEntity addMovie(MovieEntity movie);

    MovieEntity findMovieById(Integer movieId);

    List<MovieEntity> listMovies();
}
