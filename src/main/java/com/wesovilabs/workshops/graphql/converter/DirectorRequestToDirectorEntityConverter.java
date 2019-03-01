package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.domain.DirectorRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DirectorRequestToDirectorEntityConverter implements Converter<DirectorRequest, DirectorEntity> {

    @Override
    public DirectorEntity convert(DirectorRequest source) {
        return new DirectorEntity(
                source.getFullName(),
                source.getCountry()
        );
    }
}

