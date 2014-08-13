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

    //TODO: do we have a graphID here?
    @GraphId
    private Long graphId;

    @StartNode
    @Fetch
    private User user;

    @EndNode
    @Fetch private Meeting meeting;

    private String role; //TODO: to be used later

    public MeetingMembership(){};

    public MeetingMembership(User user, Meeting meeting, String role){
        this.user = user;
        this.meeting = meeting;
        this.role = role;
    }



    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    int friendsCount;

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