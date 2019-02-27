package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;

import java.util.List;

public interface ActorService {

    ActorEntity addActor(ActorEntity aentity);

    void deleteActorWithId(Integer actorId);

    List<ActorEntity> listActors();
}
