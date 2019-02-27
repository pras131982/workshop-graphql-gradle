package com.wesovilabs.workshops.graphql.resolver;

import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Query extends BaseResolver {


    public DataFetcher listActors() {
        return environment -> actorRepository
                .findAll()
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher listDirectors() {
        return environment -> directorRepository
                .findAll()
                .stream()
                .map(directorEntityToDirectorConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher listMovies() {
        return environment -> movieRepository
                .findAll()
                .stream()
                .map(movieEntityToMovieConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher getMovie() {
        return environment -> {
            Integer movieId = Integer.valueOf(environment.getArgument("movieId"));
            MovieEntity entity = movieRepository.getOne(movieId);
            return movieEntityToMovieConverter.convert(entity);
        };
    }


}
