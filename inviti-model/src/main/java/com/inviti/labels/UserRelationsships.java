package com.inviti.labels;

import org.neo4j.graphdb.RelationshipType;

/**
 * Created by vladyslavprytula on 8/8/14.
 * We have N  types of relationships:
 * Invited  users that belong to some Meeting
 * Users that know about other Users
 * User that do not know about user or meeting but have similar iterests
 * TODO: shoud we add something more?
 */
public enum  UserRelationsships implements RelationshipType{
    BELONGS_TO,
    KNOWS_OF,
    HAS_SIMILAR_INTEREST
}
