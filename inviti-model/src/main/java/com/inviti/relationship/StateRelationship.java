package com.inviti.relationship;


import com.inviti.relationship.types.RelationshipTypes;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@RelationshipEntity(type = RelationshipTypes.STATE)
public class StateRelationship<T extends Comparable<T>,V extends Comparable<V>> implements Comparable<StateRelationship<T,V>>{
    @GraphId
    private Long nodeId;

    transient private Integer hash;
    @StartNode
    @Fetch
    private  T IdentityNode;

    @EndNode
    @Fetch private V StateNode;

    private long validFrom;
    private long validTo;

    public StateRelationship() {
    }

    public StateRelationship(T identityNode, V stateNode, long validFrom, long validTo) {
        this.IdentityNode = identityNode;
        this.StateNode = stateNode;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public int compareTo(StateRelationship other) {
        return Long.compare(this.validTo, other.validTo);
    }

    public void setValidTo(long validTo){
        this.validTo = validTo;
    }
}
