package com.inviti.model;

import com.inviti.relationship.MeetingMembership;
import com.inviti.relationship.RelationshipTypes;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@NodeEntity
public class User {


    @GraphId
    private Long nodeId;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "userName")
    private String userName;

    @Indexed(unique = true)
    private String userId;

    @RelatedTo(type = RelationshipTypes.KNOWS)
    private Set<User> familiarUsers = new HashSet<>();

    @Fetch @RelatedToVia(elementClass = MeetingMembership.class, type =  RelationshipTypes.BELONGS, direction = Direction.INCOMING)
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


    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }



}
