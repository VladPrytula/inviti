package com.inviti.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.management.relation.Role;
import java.util.Set;

/**
 * Created by vladyslavprytula on 8/7/14.
 */
@NodeEntity
public class User {
    @GraphId
    Long nodeId;

    String name;

    String id;

    public User(){
        this.name = "vlad";
        this.id = "id1";
    }

    Set<Role> userRole;
}
