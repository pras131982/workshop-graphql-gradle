package com.wesovilabs.workshops.graphql.resolver;

import com.wesovilabs.workshops.graphql.converter.*;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import com.wesovilabs.workshops.graphql.database.repository.DirectorRepository;
import com.wesovilabs.workshops.graphql.database.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ResolverBase {

    @Autowired
    protected ActorRepository actorRepository;

    @Autowired
    protected ActorEntityToActorConverter actorEntityToActorConverter;

    @Autowired
    protected DirectorEntityToDirectorConverter directorEntityToDirectorConverter;

    @Autowired
    protected MovieEntityToMovieConverter movieEntityToMovieConverter;

    @Autowired
    protected DirectorRepository directorRepository;

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected MovieRequestToMovieEntityConverter movieRequestToMovieEntityConverter;

    @Autowired
    protected ActorRequestToActorEntityConverter actorRequestToActorEntityConverter;

}
