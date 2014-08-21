package com.inviti.service.metricservice;

import com.inviti.repository.graph.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inviti.model.state.User;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/18/14.
 */
@Service
public class ProximityUserImpl implements Proximity<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<User> getNearby(User user) {
        return userRepository.getNearby(user);
    }
}
