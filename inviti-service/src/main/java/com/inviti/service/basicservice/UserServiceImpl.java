package com.inviti.service.basicservice;

import com.inviti.model.User;
import com.inviti.repository.graph.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public User findUser() {
        return userRepository.findAll().iterator().next();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String name) {
        return userRepository.findByUserName(name).iterator().next();
    }

}

