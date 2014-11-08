package com.inviti.model.domainmodel;

import com.inviti.relationship.StructuralRelationship;
import com.inviti.relationship.types.RelationshipTypes;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.*;

/**
 * Created by vladyslavprytula on 8/22/14.
 * Entity Equality
 * <p/>
 * Entity equality can be a grey area, and it is debatable whether natural keys or database ids best describe equality,
 * there is the issue of versioning over time, etc. For Spring Data Neo4j we have adopted the convention that database-issued ids
 * are the basis for equality, and that has some consequences:
 * <p/>
 * 1. Before you attach an entity to the database, i.e. before the entity has had its id-field populated, we suggest you rely on object identity
 * for comparisons
 * 2. Once an entity is attached, we suggest you rely solely on the id-field for equality
 * 3. When you attach an entity, its hashcode changes - because you keep equals and hashcode consistent and rely on the database ID,
 * and because Spring Data Neo4j populates the database ID on save
 * That causes problems if you had inserted the newly created entity into a hash-based collection before saving.
 * That can be worked around, though SPRING DOC strongly advises to adopt a convention of not working with un-attached entities, to keep  code simple.
 * Meanwhile we have a work around suggested in the same spring doc
 *
 * @see <a href="http://docs.spring.io/spring-data/data-neo4j/docs/3.0.3.RELEASE/reference/html/programming-model.html">programming model</a>
 */
@NodeEntity
public class User implements Comparable<User> {
    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(unique = true)
    private String userId;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "userName")
    private String userName;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "password")
    private String password;

    @Fetch
    @RelatedToVia(type = RelationshipTypes.BELONGS)
    private Set<StructuralRelationship<User, Meeting>> userMeetingStructuralRelationships = new HashSet<>();

    @Fetch
    @RelatedToVia(type = RelationshipTypes.KNOWS)
    private Set<StructuralRelationship<User, User>> userUserStructuralRelationships = new HashSet<>();


    public User() {
        this("defaultUserId", "defaultUserName");
    }

    public User(String userId) {
        this.userId = userId;
        this.userId = "defaultUserName";
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public StructuralRelationship<User, Meeting> belongsTo(Meeting meeting, String role, long validFrom, long validTo) {
        StructuralRelationship<User, Meeting> structuralRelationship =
                new StructuralRelationship<User, Meeting>(this, meeting, role, validFrom, validTo);
        userMeetingStructuralRelationships.add(structuralRelationship);
        return structuralRelationship;
    }

    public StructuralRelationship<User, User> knowsOf(User user, long validFrom, long validTo) {
        StructuralRelationship<User, User> structuralRelationship =
                new StructuralRelationship<User, User>(this, user, validFrom, validTo);
        userUserStructuralRelationships.add(structuralRelationship);
        return structuralRelationship;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (this.getClass() != other.getClass()) return false;
        //if (! (other instanceof User)) return false;

        return nodeId.equals(((User) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public int compareTo(User o) {
        return this.userId.compareTo(o.userId);
    }
}