package com.inviti.repository.graph;

import com.inviti.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@Repository
public interface UserRepository extends GraphRepository<User>{
}
