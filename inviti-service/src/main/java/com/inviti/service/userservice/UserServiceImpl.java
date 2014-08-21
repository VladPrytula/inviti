package com.inviti.service.userservice;

import com.inviti.model.state.Meeting;
import com.inviti.model.state.User;
import com.inviti.repository.graph.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * Created by vladyslavprytula on 8/8/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String name) {
        return userRepository.findByUserName(name).iterator().next();
    }

    @Override
    public void addUserToMeeting(User user, Meeting meeting, String role) {

    }

    @Override
    public Set<User> getFriends(User user) {
        return null;
    }

    @Override
    public Set<Meeting> getFriendsMeetings(User user) {
        return null;
    }

}

