package com.wesovilabs.workshops.graphql.resolver;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Movie extends BaseResolver {

    public DataFetcher movieDirector() {
        return environment -> {
            DirectorEntity entity = movieRepository.getOne(((com.wesovilabs.workshops.graphql.domain.Movie) environment.getSource()).getId()).getDirector();
            return directorEntityToDirectorConverter.convert(entity);
        };
    }

    public DataFetcher movieActors() {
        return environment -> {
            Integer total = environment.getArgument("total");
            List<ActorEntity> actorEntities = movieRepository.getOne(((com.wesovilabs.workshops.graphql.domain.Movie) environment.getSource()).getId()).getActors();
            if (actorEntities.size() == 0) {
                return null;
            }
            total = Math.min(total, actorEntities.size());
            return actorEntities
                    .subList(0, total)
                    .stream()
                    .map(actorEntityToActorConverter::convert)
                    .collect(Collectors.toList());
        };
    }
}
