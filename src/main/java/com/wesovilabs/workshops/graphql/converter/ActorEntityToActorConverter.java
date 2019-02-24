package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.domain.Actor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorEntityToActorConverter implements Converter<ActorEntity, Actor> {

    @Override
    public Actor convert(ActorEntity source) {
        return new Actor(
                source.getId(),
                source.getFullName(),
                source.getCountry(),
                source.getMale() ? "male" : "female"
        );
    }
}
