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
        System.out.println("fwehkfhwefwehfuiwehfiuwhfuiwhefuihweiufhweuifhwieufhweiufhwieufhiweufhwieufhweiu");
        System.out.println(user.getUserName() + " " + user.getPassword());
        User loggedUser  = userService.find(user.getUserName());

        if (loggedUser != null){
            System.out.println("User name : "+ loggedUser.getUserName());
            return (loggedUser.getUserName().equals(user.getUserName()) && loggedUser.getPassword().equals(user.getUserName()));
        }
        return false;
    }
}
