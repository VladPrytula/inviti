package com.inviti.service.meetingservice;

import com.inviti.model.state.Meeting;
import com.inviti.model.state.User;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/15/14.
 */
public interface MeetingService {
    void saveMeeting(Meeting meeting);
    Set<Meeting> findMeetingByName(String meetingName);
    Set<Meeting> findMeetingByTopic(String meetingTopic);

    Set<User> getAllUsers(Meeting meeting);
    Set<User> getUserWithRole(Meeting meeting, String role);

    Set<Meeting> getSimilarMeetings(Meeting meeting);
}
