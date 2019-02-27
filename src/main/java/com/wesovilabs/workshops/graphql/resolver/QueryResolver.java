package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wesovilabs.workshops.graphql.converter.ActorEntityToActorConverter;
import com.wesovilabs.workshops.graphql.converter.DirectorEntityToDirectorConverter;
import com.wesovilabs.workshops.graphql.converter.MovieEntityToMovieConverter;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import com.wesovilabs.workshops.graphql.database.repository.DirectorRepository;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.Actor;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorEntityToActorConverter actorEntityToActorConverter;

    @Autowired
    private MovieEntityToMovieConverter movieEntityToMovieConverter;

    @Autowired
    private DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    public List<Actor> listActors() {
        return actorRepository
                .findAll()
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

    public List<Director> listDirectors() {
        return directorRepository
                .findAll()
                .stream()
                .map(directorEntityToDirectorConverter::convert)
                .collect(Collectors.toList());
    }

    public List<Movie> listMovies() {
        return movieRepository
                .findAll()
                .stream()
                .map(movieEntityToMovieConverter::convert)
                .collect(Collectors.toList());
    }

    public Movie getMovie(Integer movieId) {
        MovieEntity entity = movieRepository.getOne(movieId);
        return movieEntityToMovieConverter.convert(entity);
    }
}
