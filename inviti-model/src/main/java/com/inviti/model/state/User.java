package com.inviti.model.state;

import com.inviti.relationship.structural.MeetingMembership;
import com.inviti.relationship.types.RelationshipTypes;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@Deprecated
@NodeEntity
public class User{


    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "userName")
    private String userName;

    @Indexed(unique = true)
    private String userId;

    @RelatedTo(type = RelationshipTypes.KNOWS)
    private Set<User> familiarUsers = new HashSet<>();

    @Fetch @RelatedToVia(type =  RelationshipTypes.BELONGS, direction = Direction.INCOMING)
    private Set<MeetingMembership> memberships = new HashSet<>();

    public User(){
        this("default", "defaultId");
    }
    public  User (String userName, String userId){
        this.userName = userName;
        this.userId = userId;
    }

    public void setFamiliarUsers(Set<User> familiarUsers) {
        this.familiarUsers = familiarUsers;
    }

    public Iterable<MeetingMembership> getMemberships(){
        return memberships;
    }

    public MeetingMembership belongsTo(Meeting meeting, String role){
        MeetingMembership meetingMembership = new MeetingMembership(this,meeting,role);
        memberships.add(meetingMembership);
        return meetingMembership;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (this.getClass()!=other.getClass()) return false;
        //if (! (other instanceof User)) return false;

        return nodeId.equals(((User) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', id='%s'}", userName, userId);
    }

}
