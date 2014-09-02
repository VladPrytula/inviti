package com.inviti.rest.controller;

import com.inviti.model.domainmodel.User;
import com.inviti.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        User firstUser = new User();
        userService.save(firstUser);
        return userService.find("defaultName").getName();
/*        User defaultUser = new User();
        userService.saveUser(defaultUser);
        return userService.findUser("default").getUserName()+" "+ "user pong- pong";*/

    }
}