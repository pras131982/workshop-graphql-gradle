package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.domain.MovieRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieRequestToMovieEntityConverter implements Converter<MovieRequest, MovieEntity> {

    @Override
    public MovieEntity convert(MovieRequest source) {
        return new MovieEntity(
                source.getTitle(),
                source.getYear(),
                source.getGenre(),
                source.getBudget(),
                source.getTrailer(),
                source.getDirectorId()
        );
    }
}