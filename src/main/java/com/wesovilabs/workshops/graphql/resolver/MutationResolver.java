package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.wesovilabs.workshops.graphql.converter.DirectorEntityToDirectorConverter;
import com.wesovilabs.workshops.graphql.converter.DirectorRequestToDirectorEntityConverter;
import com.wesovilabs.workshops.graphql.converter.MovieEntityToMovieConverter;
import com.wesovilabs.workshops.graphql.converter.MovieRequestToMovieEntityConverter;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.domain.Director;
import com.wesovilabs.workshops.graphql.domain.DirectorRequest;
import com.wesovilabs.workshops.graphql.domain.Movie;
import com.wesovilabs.workshops.graphql.domain.MovieRequest;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import com.wesovilabs.workshops.graphql.service.DirectorService;
import com.wesovilabs.workshops.graphql.service.MovieService;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static graphql.ErrorType.DataFetchingException;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private DirectorRequestToDirectorEntityConverter directorRequestToDirectorEntityConverter;

    @Autowired
    private MovieRequestToMovieEntityConverter movieRequestToMovieEntityConverter;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    @Autowired
    private MovieEntityToMovieConverter movieEntityToMovieConverter;

    @Autowired
    private MovieDirectorPublisher movieDirectorPublisher;


    public Director addDirector(DirectorRequest request) {
        try {
            DirectorEntity directorEntity = directorRequestToDirectorEntityConverter.convert(request);
            directorEntity = directorService.addDirector(directorEntity);
            return directorEntityToDirectorConverter.convert(directorEntity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Director> deleteDirector(Integer directorId) {
        directorService.deleteDirectorWithId(Integer.valueOf(directorId));
        return directorService
                .listDirectors()
                .stream()
                .map(directorEntityToDirectorConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public Movie addMovie(MovieRequest request, DataFetchingEnvironment env) {
        try {
            MovieEntity movieEntity = movieRequestToMovieEntityConverter.convert(request);
            movieEntity = movieService.addMovie(movieEntity);
            movieEntity.getDirector();
            movieDirectorPublisher.publish(movieEntity);
            return movieEntityToMovieConverter.convert(movieEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            // The purpose of this block is just showing how we could customize the errors
            env.getExecutionContext().addError(customError(ex));
            throw ex;
        }
    }

    private GraphQLError customError(Exception ex) {
        return new GraphQLError() {
            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            @Override
            public ErrorType getErrorType() {
                return DataFetchingException;
            }
        };
    }
}
