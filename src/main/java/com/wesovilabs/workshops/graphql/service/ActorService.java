package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;

import java.util.List;

public interface ActorService {

    List<ActorEntity> listActors();
}
