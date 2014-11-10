package com.inviti.rest.controller;

import com.inviti.model.domainmodel.User;
import com.inviti.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Created by roman.tokmakov on 11/7/2014.
 */
@RestController
@RequestMapping("/login")
@Secured("USER")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean login(@RequestBody User user) {
        //TODO just a temp code - should be replaced with a basic user validation
        User loggedUser  = userService.find(user.getUserName());
        if (loggedUser != null && user != null){
            if (loggedUser.getUserName().equals(user.getUserName()) && loggedUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
