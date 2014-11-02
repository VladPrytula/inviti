package com.inviti.model.domainmodel;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;


/**
 * Created by vladyslavprytula on 8/22/14.
 */
@NodeEntity
public class Meeting implements Comparable<Meeting>{
    @GraphId
    private Long nodeId;

    transient private Integer hash;

    @Indexed(unique = true)
    private String meetingId;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "meetingName")
    private String meetingName;


    public Meeting() {
        this("defaultMeetingId","defaultMeetingName");
    }

    public Meeting(String meetingId) {
        this.meetingId = meetingId;
        this.meetingName = "defaultMeetingName";
    }
    public Meeting(String meetingId,String meetingName) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
    }

    public String getMeetingName() {
        return meetingName;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (nodeId == null) return false;

        if (this.getClass()!=other.getClass()) return false;
        //if (! (other instanceof User)) return false;

        return nodeId.equals(((Meeting) other).nodeId);

    }

    @Override
    public int hashCode() {
        if (hash == null) hash = nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
        return hash.hashCode();
    }

    @Override
    public int compareTo(Meeting o) {
        return this.meetingId.compareTo(o.meetingId);
    }

    @Override
    public String toString() {
        return String.format("Meeting{ name='%s'}", meetingName);
    }
}