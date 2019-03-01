package com.wesovilabs.workshops.graphql.service;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import com.wesovilabs.workshops.graphql.database.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public DirectorEntity addDirector(DirectorEntity aentity) {
        return directorRepository.save(aentity);
    }

    @Override
    public void deleteDirectorWithId(Integer directorId) {
        directorRepository.deleteById(directorId);
    }
    
    @Override
    public List<DirectorEntity> listDirectors() {
        return directorRepository.findAll();
    }
}
