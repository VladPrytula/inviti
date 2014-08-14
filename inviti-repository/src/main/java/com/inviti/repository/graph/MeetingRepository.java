package com.inviti.repository.graph;

import com.inviti.model.Meeting;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@Repository
public interface MeetingRepository extends GraphRepository<Meeting>{
    List<Meeting> findByMeetingName(String meetingName);
}
