package com.wesovilabs.workshops.graphql.resolver;

import com.wesovilabs.workshops.graphql.converter.ActorEntityToActorConverter;
import com.wesovilabs.workshops.graphql.converter.DirectorEntityToDirectorConverter;
import com.wesovilabs.workshops.graphql.converter.MovieEntityToMovieConverter;
import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import com.wesovilabs.workshops.graphql.database.repository.DirectorRepository;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.Actor;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.domain.Movie;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Query extends ResolverBase {


    public DataFetcher listActors() {
        return dataFetchingEnvironment -> actorRepository
                .findAll()
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher listDirectors() {
        return dataFetchingEnvironment -> directorRepository
                .findAll()
                .stream()
                .map(directorEntityToDirectorConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher listMovies() {
        return dataFetchingEnvironment -> movieRepository
                .findAll()
                .stream()
                .map(movieEntityToMovieConverter::convert)
                .collect(Collectors.toList());
    }

    public DataFetcher getMovie() {
        return dataFetchingEnvironment -> {
            return null;
        };
    }

    public DataFetcher movieDirector() {
        return environment -> {
            DirectorEntity entity = movieRepository.getOne(((Movie) environment.getSource()).getId()).getDirector();
            return directorEntityToDirectorConverter.convert(entity);
        };
    }

    public DataFetcher movieActors() {
        return environment -> {
            Integer total = environment.getArgument("total");
            List<ActorEntity> actorEntities = movieRepository.getOne(((Movie) environment.getSource()).getId()).getActors();
            if (actorEntities.size() == 0) {
                return null;
            }
            total = Math.min(total,actorEntities.size());
            return actorEntities
                    .subList(0, total)
                    .stream()
                    .map(actorEntityToActorConverter::convert)
                    .collect(Collectors.toList());
        };
    }
}
