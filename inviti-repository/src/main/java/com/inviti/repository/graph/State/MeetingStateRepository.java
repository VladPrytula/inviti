package com.inviti.repository.graph.State;

import com.inviti.model.state.MeetingStateNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@Repository
public interface MeetingStateRepository extends GraphRepository<MeetingStateNode> {
}
