package com.inviti.service.userservice;


import com.inviti.model.domainmodel.Meeting;
import com.inviti.model.domainmodel.User;

/**
 * Created by vladyslavprytula on 8/8/14.
 */
public interface UserService {
    void save (User user);
    User find(String userName);
    void assignToMeeting(User user, Meeting meeting, String role);
}
