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
import com.wesovilabs.workshops.graphql.service.ActorService;
import com.wesovilabs.workshops.graphql.service.MovieService;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static graphql.ErrorType.DataFetchingException;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private ActorRequestToActorEntityConverter actorRequestToActorEntityConverter;

    @Autowired
    private MovieRequestToMovieEntityConverter movieRequestToMovieEntityConverter;

    @Autowired
    private ActorService actorService;

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
            actorEntity = actorService.addActor(actorEntity);
            return actorEntityToActorConverter.convert(actorEntity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Actor> deleteActor(Integer actorId) {
        actorService.deleteActorWithId(Integer.valueOf(actorId));
        return actorService
                .listActors()
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public Movie addMovie(MovieRequest request, DataFetchingEnvironment env) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            MovieRequest movieRequest = mapper.convertValue(request, MovieRequest.class);
            MovieEntity movieEntity = movieRequestToMovieEntityConverter.convert(movieRequest);
            movieEntity = movieService.addMovie(movieEntity);
            movieEntity.getDirector();
            movieDirectorPublisher.publish(movieEntity);
            return movieEntityToMovieConverter.convert(movieEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            // The purpose of this block is just showing how we could customize the errors
            env.getExecutionContext().addError(customError(ex));
            throw ex;
        }
    }

    private GraphQLError customError(Exception ex) {
        return new GraphQLError() {
            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            @Override
            public ErrorType getErrorType() {
                return DataFetchingException;
            }
        };
    }
}
