package com.inviti.repository.graph;

import com.inviti.model.Meeting;
import com.inviti.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@Repository
public interface UserRepository extends GraphRepository<User>{
    List<User> findByUserName(String name);
/*
    @Query("START user = node:User(userName={userName}) "+
            "MATCH user-[know:KNOWS]->otherUser "+
            "RETURN otherUser ")*/
   @Query("MATCH (u: User {userName:{userName}})-[know:KNOWS]->(otherUser) RETURN otherUser")
    List<User> findFriends(@Param("userName") String userName);

 //   Set<User> findByMeeting(Meeting meeting);

 //    Set<User> findCollaborators(User user);
}
