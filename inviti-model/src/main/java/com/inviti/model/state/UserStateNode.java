package com.inviti.model.state;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;


/**
 * Created by vladyslavprytula on 8/7/14.
 */
@NodeEntity
public class UserStateNode implements Comparable<UserStateNode>{


    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "userName")
    private String userName;

    public UserStateNode(){
        this("default");
    }

    public UserStateNode(String userName){
        this.userName = userName;
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

        return nodeId.equals(((UserStateNode) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public String toString() {
        return String.format("User{name='%s'}", userName);

    }

    @Override
    public int compareTo(UserStateNode o) {
        return this.userName.compareTo(o.userName);
    }
}
