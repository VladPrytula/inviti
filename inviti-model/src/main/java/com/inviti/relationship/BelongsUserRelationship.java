package com.inviti.relationships;

import com.inviti.model.User;
import com.inviti.relationship.RelationshipTypes;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by vladyslavprytula on 8/12/14.
 */
@RelationshipEntity(type = RelationshipTypes.BELONGS)
public class BelongsUserRelationship {
    @StartNode
    @Fetch
    User start;

    @EndNode

    @Fetch
    User end;

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    int friendsCount;

    public User getStart() {
        return start;
    }

    public void setStart(User start) {
        this.start = start;
    }

    public User getEnd() {
        return end;
    }

    public void setEnd(User end) {
        this.end = end;
    }


}
