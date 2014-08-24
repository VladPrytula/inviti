package com.inviti.model.domainmodel;

import com.inviti.model.identity.UserIdentityNode;
import com.inviti.model.state.UserStateNode;

import java.util.Date;

/**
 * Created by vladyslavprytula on 8/22/14.
 * Due to the fact that I have separated entities into their ID and State it looks like we are facing some kind of O-R impedance mismatch
 * This leads DM classes that aggregates Id and State.
 * It is supposed that REST layer will operate in terms of DM classes, while services and repositories will operate on IDs and States
 * Additionally we are "separating" "behaviour" from "state"
 */
public class User {
    private UserIdentityNode userIdentityNode;
    private UserStateNode currentUserStateNode;

    public User(){
        this("defaultId", "defaultName");
    };

    public User(UserIdentityNode userIdentityNode, UserStateNode userStateNode){
        this.userIdentityNode = userIdentityNode;
        this.currentUserStateNode = userStateNode;
        /**
         * set state relationship TODO: this should not be here
         * this is related to repository, orm, persistence layers. not to the Domain.
         * getHisotyr method would also belong to service, repository layer.
         */
        userIdentityNode.setNewState(currentUserStateNode, new Date().getTime(), Long.MAX_VALUE);
    }

    public User(String userId, String userName){
        this.userIdentityNode = new UserIdentityNode(userId);
        this.currentUserStateNode = new UserStateNode(userName);
        /**
         * set state relationship
         */
        userIdentityNode.setNewState(currentUserStateNode, new Date().getTime(), Long.MAX_VALUE);
    }

    public String getName() {
        return currentUserStateNode.getUserName();
    }

    public String getId() {
        return userIdentityNode.getUserId();
    }

    public UserIdentityNode getUserIdentityNode() {
        return userIdentityNode;
    }

    public UserStateNode getCurrentUserStateNode() {
        return currentUserStateNode;
    }
}
