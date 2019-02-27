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
import com.wesovilabs.workshops.graphql.service.ActorService;
import com.wesovilabs.workshops.graphql.service.DirectorService;
import com.wesovilabs.workshops.graphql.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ActorService actorService;
    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorEntityToActorConverter actorEntityToActorConverter;

    @Autowired
    private MovieEntityToMovieConverter movieEntityToMovieConverter;

    @Autowired
    private DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    public List<Actor> listActors() {
        return actorService
                .listActors()
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

    public List<Director> listDirectors() {
        return directorService
                .listDirectors()
                .stream()
                .map(directorEntityToDirectorConverter::convert)
                .collect(Collectors.toList());
    }

    public List<Movie> listMovies() {
        return movieService
                .listMovies()
                .stream()
                .map(movieEntityToMovieConverter::convert)
                .collect(Collectors.toList());
    }

    public Movie getMovie(Integer movieId) {
        MovieEntity entity = movieService.findMovieById(movieId);
        return movieEntityToMovieConverter.convert(entity);
    }
}
