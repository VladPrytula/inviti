package com.inviti.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@NodeEntity
public class Interest {
    @GraphId
    private Long nodeId;
}
