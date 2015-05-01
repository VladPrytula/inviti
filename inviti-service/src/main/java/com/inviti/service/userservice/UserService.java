package com.inviti.service.userservice;


import com.inviti.model.domainmodel.Meeting;
import com.inviti.model.domainmodel.User;

public interface UserService {
    void save (User user);
    User find(String userName);
    void assignToMeeting(User user, Meeting meeting, String role);
}
