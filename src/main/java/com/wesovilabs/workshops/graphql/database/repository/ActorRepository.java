package com.wesovilabs.workshops.graphql.database.repository;

import com.wesovilabs.workshops.graphql.database.model.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity,Long> {
}
