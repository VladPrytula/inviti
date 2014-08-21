package com.inviti.service.userservice;

import com.inviti.model.state.Meeting;
import com.inviti.model.state.User;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/8/14.
 */
public interface UserService {
    void saveUser(User user);
    User findUser(String name);
    void addUserToMeeting(User user, Meeting meeting, String role);
    Set<User> getFriends(User user);
    Set<Meeting> getFriendsMeetings(User user);

}
