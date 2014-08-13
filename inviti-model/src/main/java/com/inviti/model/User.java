package com.inviti.model;

import com.inviti.relationship.RelationshipTypes;
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
    Long nodeId;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "userName")
    private String userName;

    @Indexed(unique = true)
    private String userId;

    public Set<User> getFamiliarUsers() {
        return familiarUsers;
    }

    public void setFamiliarUsers(Set<User> familiarUsers) {
        this.familiarUsers = familiarUsers;
    }

    @RelatedTo(type = RelationshipTypes.KNOWS)
    private Set<User> familiarUsers = new HashSet<>();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public String getUserId() {
        return userId;
    }


    public User(){
        this("default", "defaultId");
    }
    public  User (String userName, String userId){
        this.userName = userName;
        this.userId = userId;
    }
}
