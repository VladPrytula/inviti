package com.inviti.model.identity;

import com.inviti.model.state.MeetingStateNode;
import com.inviti.relationship.StateRelationship;
import com.inviti.relationship.types.RelationshipTypes;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/19/14.
 */
@NodeEntity
public class MeetingIdentityNode implements Comparable<MeetingIdentityNode>{
    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(unique = true)
    private String meetingId;

    @Fetch
    @RelatedToVia(type =  RelationshipTypes.STATE)
    private Set<StateRelationship<MeetingIdentityNode,MeetingStateNode>> meetingStateRelationships = new HashSet<>();


    public MeetingIdentityNode() {
        this("defaultMeetingIdNode");
    }

    public MeetingIdentityNode(String meetingId) {
        this.meetingId = meetingId;
    }

    public StateRelationship<MeetingIdentityNode,MeetingStateNode> setNewState(MeetingStateNode meetingStateNode, long validFrom, long validTo){
        StateRelationship<MeetingIdentityNode,MeetingStateNode> meetingStateRelationship =
                new StateRelationship<>(this, meetingStateNode, validFrom, validTo);
        meetingStateRelationships.add(meetingStateRelationship);
        return meetingStateRelationship;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (this.getClass()!=other.getClass()) return false;
        //if (! (other instanceof User)) return false;

        return nodeId.equals(((MeetingIdentityNode) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public int compareTo(MeetingIdentityNode o) {
        return this.meetingId.compareTo(o.meetingId);
    }
}
