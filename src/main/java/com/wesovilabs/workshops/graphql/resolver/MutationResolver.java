package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesovilabs.workshops.graphql.converter.*;
import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.Actor;
import com.wesovilabs.workshops.graphql.domain.ActorRequest;
import com.wesovilabs.workshops.graphql.domain.MovieRequest;
import com.wesovilabs.workshops.graphql.domain.Movie;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private MovieRepository movieRepository;

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

    public Movie addMovie(MovieRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            MovieRequest movieRequest = mapper.convertValue(request, MovieRequest.class);
            MovieEntity movieEntity = movieRequestToMovieEntityConverter.convert(movieRequest);
            movieRepository.save(movieEntity);
            Movie movie = movieEntityToMovieConverter.convert(movieEntity);
            movie.setDirector(directorEntityToDirectorConverter.convert(movieEntity.getDirector()));
            movieDirectorPublisher.publish(movie);
            return movie;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
