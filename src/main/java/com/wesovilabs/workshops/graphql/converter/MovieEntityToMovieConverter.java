package com.wesovilabs.workshops.graphql.converter;

import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.domain.Movie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieEntityToMovieConverter implements Converter<MovieEntity, Movie> {

    @Override
    public Movie convert(MovieEntity source) {
        return new Movie(
                source.getId(),
                source.getTitle(),
                source.getReleaseYear(),
                source.getGenre(),
                source.getBudget(),
                source.getThriller()
        );
    }
}
