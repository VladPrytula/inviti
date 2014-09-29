package com.inviti.relationship;

import com.inviti.relationship.types.RelationshipTypes;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@RelationshipEntity(type = RelationshipTypes.BELONGS)
public class StructuralRelationship<T,V> {
    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @StartNode
    @Fetch
    private T nodeStart;

    @EndNode
    @Fetch private V nodeEnd;

    private long validFrom;

    private long validTo;

    private String role;

    public StructuralRelationship() {
    }

    public StructuralRelationship(T nodeStart, V nodeEnd, long validFrom, long validTo) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public StructuralRelationship(T nodeStart, V nodeEnd, String role, long validFrom, long validTo) {
        this(nodeStart,nodeEnd,validFrom,validTo);
        this.role = role;
    }

    public void setValidTo(long validTo) {
        this.validTo = validTo;
    }
    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

/*    @Override
    public boolean equals(Object other) {

        if (this == other) return true;

        if (nodeId == null) return false;

        if (! (other instanceof StructuralRelationship)) return false;

        return nodeId.equals(((StructuralRelationship<T,V>) other).nodeId);

    }*/
}
