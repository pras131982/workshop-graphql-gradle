package com.wesovilabs.workshops.graphql.database.repository;

import com.wesovilabs.workshops.graphql.database.model.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
}

