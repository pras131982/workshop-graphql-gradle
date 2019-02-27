package com.wesovilabs.workshops.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.wesovilabs.workshops.graphql.publisher.MovieDirectorPublisher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

    @Autowired
    private MovieDirectorPublisher movieDirectorPublisher;

    public DataFetcher listenDirectorMovies() {

        return environment -> {
            List<String> arg = environment.getArgument("directorId");
            return movieDirectorPublisher.getPublisher();
        };
    }


}
