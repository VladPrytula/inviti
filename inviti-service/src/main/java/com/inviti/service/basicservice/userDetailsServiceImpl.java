package com.inviti.service.basicservice;

import com.inviti.model.User;
import com.inviti.repository.graph.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by vladyslavprytula on 8/8/14.
 */
@Service
public class userDetailsServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser() {
        return userRepository.findAll().single();
    }

}

