package com.inviti.relationship;

import com.inviti.model.Meeting;
import com.inviti.model.User;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by vladyslavprytula on 8/12/14.
 * This one’s more interesting than the KNOWS relationship because
 * MEMBER_OF has an associated property–role–that’s analogous to adding a column to a link table in a RDBMS,
 * The User.belongsTo () method provides a way to assign a user to a meeting using a special MeetingMembership “relationship entity”
 */
@RelationshipEntity(type = RelationshipTypes.BELONGS)
public class MeetingMembership {

    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @StartNode
    @Fetch
    private User user;

    @EndNode
    @Fetch private Meeting meeting;

    /*
    *This defines the role the user is assigned in a meeting. Perhaps must be a enum.
     */
    private String role;

    public MeetingMembership(){};

    public MeetingMembership(User user, Meeting meeting, String role){
        this.user = user;
        this.meeting = meeting;
        this.role = role;
    }


    @Override
    public boolean equals(Object other) {

        if (this == other) return true;

        if (nodeId == null) return false;

        if (! (other instanceof MeetingMembership)) return false;

        return nodeId.equals(((MeetingMembership) other).nodeId);

    }
    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public String toString() {
        return String.format("MeetingMembership{ meeting='%s'}", meeting);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

}