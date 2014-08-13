package com.inviti.service.basicservice;

import com.inviti.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vladyslavprytula on 8/8/14.
 */
public interface UserService {
    void saveUser(User user);
    User findUser();
    User findUser(String name);

}
