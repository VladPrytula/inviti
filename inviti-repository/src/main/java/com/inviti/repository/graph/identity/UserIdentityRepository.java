package com.inviti.repository.graph.identity;

import com.inviti.model.identity.UserIdentityNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@Repository
public interface UserIdentityRepository extends GraphRepository<UserIdentityNode>{
        @Query("MATCH (uState:UserStateNode {userName:{userName}})<-[:STATE]-UserIdentityNode "+
                "RETURN UserIdentityNode")
        public Set<UserIdentityNode> findByUserName(@Param("userName") String userName);
}
