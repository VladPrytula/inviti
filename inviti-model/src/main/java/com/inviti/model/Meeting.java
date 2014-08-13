package com.inviti.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@NodeEntity
public class Meeting {
    @GraphId
    private Long nodeId;

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
    public String toString(){
        return "meeting";
    }
}
