package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wesovilabs.workshops.graphql.converter.ActorEntityToActorConverter;
import com.wesovilabs.workshops.graphql.converter.DirectorEntityToDirectorConverter;
import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import com.wesovilabs.workshops.graphql.domain.Actor;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.domain.Movie;
import com.wesovilabs.workshops.graphql.service.MovieService;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class MovieResolver implements GraphQLResolver<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    @Autowired
    private ActorEntityToActorConverter actorEntityToActorConverter;

    public Director director(Movie movie) {
        MovieEntity movieEntity = movieRepository.getOne(movie.getId());
        DirectorEntity director = movieEntity.getDirector();
        return directorEntityToDirectorConverter.convert(director);
    }

    public List<Actor> actors(Movie movie, Integer total) {
        List<ActorEntity> actorEntities = movieRepository.getOne(movie.getId()).getActors();
        if (actorEntities.size() == 0) {
            return null;
        }
        total = Math.min(total, actorEntities.size());
        return actorEntities
                .subList(0, total)
                .stream()
                .map(actorEntityToActorConverter::convert)
                .collect(Collectors.toList());
    }

}
