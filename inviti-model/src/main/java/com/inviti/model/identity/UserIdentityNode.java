package com.inviti.model.identity;

import com.inviti.model.state.UserStateNode;
import com.inviti.relationship.StateRelationship;
import com.inviti.relationship.StructuralRelationship;
import com.inviti.relationship.types.RelationshipTypes;
import org.springframework.data.neo4j.annotation.*;

import java.util.*;

/**
 * Created by vladyslavprytula on 8/19/14.
 */
@NodeEntity
public class UserIdentityNode implements Comparable<UserIdentityNode>{
    @GraphId
    private Long nodeId;

    transient private Integer hash;



    @Indexed(unique = true)
    private String userId;


    @Fetch
    @RelatedToVia(type = RelationshipTypes.STATE)
    private SortedSet<StateRelationship<UserIdentityNode,UserStateNode>> userStateRelationships= new TreeSet<>(); //TODO: not effective.

    @Fetch
    @RelatedToVia(type = RelationshipTypes.BELONGS)
    private Set<StructuralRelationship<UserIdentityNode,MeetingIdentityNode>> userMeetingStructuralRelationships = new HashSet<>();

    public UserIdentityNode() {
        this("defaultUserIdNode");
    }

    public UserIdentityNode(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public StateRelationship<UserIdentityNode,UserStateNode> setNewState(UserStateNode userStateNode, long validFrom, long validTo){
        StateRelationship<UserIdentityNode,UserStateNode> stateRelationship=
                new StateRelationship<>(this, userStateNode, validFrom, validTo);
        if(!userStateRelationships.isEmpty()) {
            userStateRelationships.last().setValidTo(validFrom);
        }
        userStateRelationships.add(stateRelationship);
        return stateRelationship;
    }


    public StructuralRelationship<UserIdentityNode,MeetingIdentityNode> belongsTo(MeetingIdentityNode meetingIdentityNode, String role, long validFrom, long validTo){
        StructuralRelationship<UserIdentityNode,MeetingIdentityNode> structuralRelationship =
                new StructuralRelationship<UserIdentityNode,MeetingIdentityNode>(this, meetingIdentityNode, role, validFrom, validTo);
        userMeetingStructuralRelationships.add(structuralRelationship);
        return structuralRelationship;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (this.getClass()!=other.getClass()) return false;
        //if (! (other instanceof User)) return false;

        return nodeId.equals(((UserIdentityNode) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public int compareTo(UserIdentityNode o) {
        return this.userId.compareTo(o.userId);
    }
}
