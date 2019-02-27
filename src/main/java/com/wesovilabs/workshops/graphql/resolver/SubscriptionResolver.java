package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.wesovilabs.workshops.graphql.domain.Movie;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    @Autowired
    private MovieDirectorPublisher movieDirectorPublisher;

    public Publisher<Movie> listenDirectorMovies(Integer directorId) {
        return movieDirectorPublisher.getPublisher(directorId);
    }

}
