package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesovilabs.workshops.graphql.converter.*;
import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.*;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import com.wesovilabs.workshops.graphql.service.MovieService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private ActorRequestToActorEntityConverter actorRequestToActorEntityConverter;

    @Autowired
    private MovieRequestToMovieEntityConverter movieRequestToMovieEntityConverter;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorEntityToActorConverter actorEntityToActorConverter;

    @Autowired
    private DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    @Autowired
    private MovieEntityToMovieConverter movieEntityToMovieConverter;

    @Autowired
    private MovieDirectorPublisher movieDirectorPublisher;

    public Actor addActor(ActorRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ActorRequest actorRequest = mapper.convertValue(request, ActorRequest.class);
            ActorEntity actorEntity = actorRequestToActorEntityConverter.convert(actorRequest);
            actorRepository.save(actorEntity);
            return actorEntityToActorConverter.convert(actorEntity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Actor> deleteActor(Integer actorId) {
        actorRepository.deleteById(Integer.valueOf(actorId));
        return queryResolver.listActors();
    }

    @Transactional
    public Movie addMovie(MovieRequest request, DataFetchingEnvironment env) {
        Movie movie = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            MovieRequest movieRequest = mapper.convertValue(request, MovieRequest.class);
            MovieEntity movieEntity = movieRequestToMovieEntityConverter.convert(movieRequest);
            movieEntity = movieService.addMovie(movieEntity);
            movie = movieEntityToMovieConverter.convert(movieEntity);
            Director director = new Director(movieEntity.getDirector().getId());
            movie.setDirector(director);
            return movie;
        } catch (Exception ex) {
            throw ex;
        } finally {
            movieDirectorPublisher.publish(movie);
        }
    }
}
