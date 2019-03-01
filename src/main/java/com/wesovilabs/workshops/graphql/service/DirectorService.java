package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;

import java.util.List;

public interface DirectorService {

    DirectorEntity addDirector(DirectorEntity entity);

    void deleteDirectorWithId(Integer directorId);

    List<DirectorEntity> listDirectors();
}
