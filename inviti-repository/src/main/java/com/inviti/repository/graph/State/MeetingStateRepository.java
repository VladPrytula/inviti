package com.inviti.repository.graph.State;

import com.inviti.model.state.MeetingStateNode;
import com.inviti.model.state.UserStateNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@Repository
public interface MeetingStateRepository extends GraphRepository<MeetingStateNode> {
    public Set<UserStateNode> findByMeetingName(String meetingName);
}
