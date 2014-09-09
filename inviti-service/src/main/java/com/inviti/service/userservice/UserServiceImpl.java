package com.inviti.service.userservice;


import com.inviti.model.domainmodel.Meeting;
import com.inviti.model.domainmodel.User;
import com.inviti.repository.graph.State.UserStateRepository;
import com.inviti.repository.graph.identity.UserIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by vladyslavprytula on 8/8/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserStateRepository userStateRepository;
    @Autowired
    UserIdentityRepository userIdentityRepository;

    @Override
    @Transactional
    public void save(User user) {
        //first relationship between state and id was created in constructor. save the initial state
        //omit verification for now
        userStateRepository.save(user.getCurrentUserStateNode());
        userIdentityRepository.save(user.getUserIdentityNode());
    }

    @Override
    public User find(String userName) {
        //TODO: incorrect in general since I pass the firs element in set to consturctor
        return new User(userIdentityRepository.findByUserName(userName).iterator().next().getUserId(),
                userStateRepository.findByUserName(userName).iterator().next().getUserName());
    }

    @Override
    public void assignToMeeting(User user, Meeting meeting, String role) {

    }
}

