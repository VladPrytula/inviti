package com.inviti.rest.controller;

import com.inviti.model.User;
import com.inviti.service.basicservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User add() {
        User user = new User();
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String name) {
        return userService.findUser(name);
    }
}
