package com.inviti.service.userservice;


import com.inviti.model.domainmodel.Meeting;
import com.inviti.model.domainmodel.User;
import com.inviti.repository.graph.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by vladyslavprytula on 8/8/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void save(User user) {
        //first relationship between state and id was created in constructor. save the initial state
        //omit verification for now
        userRepository.save(user);
    }

    @Override
    public User find(String userName) {
        return userRepository.findByUserName(userName).iterator().next(); //TODO: might be zero.
    }

    @Override
    public void assignToMeeting(User user, Meeting meeting, String role) {

    }
}

