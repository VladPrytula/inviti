package com.inviti.repository.graph;

import com.inviti.model.state.Meeting;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@Deprecated
@Repository
public interface MeetingRepository extends GraphRepository<Meeting>{
    List<Meeting> findByMeetingName(String meetingName);

    @Query("MATCH (u:User {userName:{userName}})-[:BELONGS]->meeting<-[:BELONGS]-collaborator "+
            "RETURN collaborator")
    Set<Meeting> getNearby(@Param("user") Meeting meeting); //TODO: not covered by test
}
