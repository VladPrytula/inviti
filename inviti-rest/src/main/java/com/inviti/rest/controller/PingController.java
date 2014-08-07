package com.inviti.rest.controller;

import com.inviti.model.User;
import com.inviti.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@Transactional //TODO: added in order to handle userRepository @findAll method, since they are transactional
public class PingController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        User vlad = new User();
        userRepository.save(vlad);
        return userRepository.findAll().single().getName() + " "+"pong";//
    }
}
