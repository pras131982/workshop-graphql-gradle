package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.domain.Actor;
import com.wesovilabs.workshops.graphql.domain.ActorRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorRequestToActorEntityConverter implements Converter<ActorRequest, ActorEntity> {

    @Override
    public ActorEntity convert(ActorRequest source) {
        Boolean male = false;
        if (source.getGender() != null) {
            male = source.getGender().equals("male");
        }
        return new ActorEntity(
                source.getFullName(),
                source.getCountry(),
                male
        );
    }
}

