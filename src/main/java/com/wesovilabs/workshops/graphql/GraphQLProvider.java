package com.wesovilabs.workshops.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.wesovilabs.workshops.graphql.resolver.Mutation;
import com.wesovilabs.workshops.graphql.resolver.Query;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    static String schemaPath = "workshop.graphql";

    private GraphQL graphQL;

    @Autowired
    private Mutation mutation;

    @Autowired
    private Query query;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource(schemaPath);
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("listActors", query.listActors())
                        .dataFetcher("listDirectors", query.listDirectors())
                        .dataFetcher("listMovies", query.listMovies())
                        .dataFetcher("getMovie", query.getMovie())
                )
                .type(newTypeWiring("Mutation")
                        .dataFetcher("addActor", mutation.addActor())
                        .dataFetcher("deleteActor", mutation.deleteActor())
                        .dataFetcher("addMovie", mutation.addMovie())
                )
                .type(newTypeWiring("Movie")
                        .dataFetcher("director",query.movieDirector())

                )
                .build();
    }

}