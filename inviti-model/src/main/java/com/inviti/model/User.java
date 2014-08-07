package com.inviti.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@NodeEntity
public class User {
    @GraphId
    Long nodeId;

    public String getName() {
        return name;
    }

    String name;

    String id;

    public User(){
        this.name = "vlad";
        this.id = "id1";
    }
}
