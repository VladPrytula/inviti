package com.inviti.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * Created by vladyslavprytula on 8/13/14.
 * Entity Equality
 *
 * Entity equality can be a grey area, and it is debatable whether natural keys or database ids best describe equality,
 * there is the issue of versioning over time, etc. For Spring Data Neo4j we have adopted the convention that database-issued ids
 * are the basis for equality, and that has some consequences:
 *
 * 1. Before you attach an entity to the database, i.e. before the entity has had its id-field populated, we suggest you rely on object identity
 *    for comparisons
 * 2. Once an entity is attached, we suggest you rely solely on the id-field for equality
 * 3. When you attach an entity, its hashcode changes - because you keep equals and hashcode consistent and rely on the database ID,
 *    and because Spring Data Neo4j populates the database ID on save
 * That causes problems if you had inserted the newly created entity into a hash-based collection before saving.
 * That can be worked around, though SPRING DOC strongly advises to adopt a convention of not working with un-attached entities, to keep  code simple.
 * Meanwhile we have a work around suggested in the same spring doc
 * @see <a href="http://docs.spring.io/spring-data/data-neo4j/docs/3.0.3.RELEASE/reference/html/programming-model.html">programming model</a>
 */
@NodeEntity
public class Meeting {
    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "meetingName")
    private String meetingName;

    @Indexed(unique = true)
    private String meetingId;

    public Meeting() {
        this("defaultMeeting", "defaultMeetingId");
    }

    public Meeting(String meetingName, String meetingId) {
        this.meetingName = meetingName;
        this.meetingId = meetingId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (! (other instanceof Meeting)) return false;

        return nodeId.equals(((Meeting) other).nodeId);

    }
    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Meeting{ name='%s', name='%s'}", meetingName, meetingId);
    }


}
