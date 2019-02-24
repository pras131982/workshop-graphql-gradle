package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.domain.Director;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DirectorEntityToDirectorConverter implements Converter<DirectorEntity, Director> {

    @Override
    public Director convert(DirectorEntity source) {
        return new Director(
                source.getId(),
                source.getFullName(),
                source.getCountry()
        );
    }
}
