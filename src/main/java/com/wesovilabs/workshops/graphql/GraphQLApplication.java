package com.wesovilabs.workshops.graphql;

import com.wesovilabs.workshops.graphql.resolver.Mutation;
import com.wesovilabs.workshops.graphql.resolver.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }


}
