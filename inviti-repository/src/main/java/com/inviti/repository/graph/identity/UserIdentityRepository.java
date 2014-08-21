package com.inviti.repository.graph.identity;

import com.inviti.model.identity.UserIdentityNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@Repository
public interface UserIdentityRepository extends GraphRepository<UserIdentityNode> {
}
