package com.wesovilabs.workshops.graphql.database.repository;

import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
