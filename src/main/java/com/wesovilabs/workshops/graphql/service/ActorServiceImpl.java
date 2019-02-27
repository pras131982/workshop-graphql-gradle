package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public ActorEntity addActor(ActorEntity aentity) {
        return actorRepository.save(aentity);
    }

    @Override
    public void deleteActorWithId(Integer actorId) {
        actorRepository.deleteById(actorId);
    }

    @Override
    public List<ActorEntity> listActors() {
        return actorRepository.findAll();
    }
}
