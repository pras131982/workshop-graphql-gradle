package com.wesovilabs.workshops.graphql.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.domain.ActorRequest;
import com.wesovilabs.workshops.graphql.domain.MovieRequest;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;

@Component
public class Mutation extends BaseResolver {


    @Autowired
    private Query query;

    public DataFetcher addActor() {
        return environment -> {
            LinkedHashMap<String, Object> request = environment.getArgument("request");
            ObjectMapper mapper = new ObjectMapper();
            try {
                ActorRequest actorRequest = mapper.convertValue(request, ActorRequest.class);
                ActorEntity actorEntity = actorRequestToActorEntityConverter.convert(actorRequest);
                actorRepository.save(actorEntity);
                return actorEntityToActorConverter.convert(actorEntity);
            } catch (Exception ex) {
                throw ex;
            }
        };
    }

    public DataFetcher deleteActor() {
        return environment -> {
            String actorId = environment.getArgument("actorId");
            actorRepository.deleteById(Integer.valueOf(actorId));
            return query.listActors().get(environment);

        };
    }

    public DataFetcher addMovie() {
        return environment -> {
            LinkedHashMap<String, Object> request = environment.getArgument("request");
            ObjectMapper mapper = new ObjectMapper();
            try {
                MovieRequest movieRequest = mapper.convertValue(request, MovieRequest.class);
                MovieEntity movieEntity = movieRequestToMovieEntityConverter.convert(movieRequest);
                movieRepository.save(movieEntity);
                return movieEntityToMovieConverter.convert(movieEntity);
            } catch (Exception ex) {
                throw ex;
            }

        };
    }
}
