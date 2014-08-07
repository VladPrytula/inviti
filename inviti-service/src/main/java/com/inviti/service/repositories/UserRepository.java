package com.inviti.service.repositories;

import com.inviti.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@Repository
public interface UserRepository extends GraphRepository<User>{
}
