package com.inviti.rest.controller;

import com.inviti.model.User;
import com.inviti.service.repositories.UserRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {
    @Autowired
    GraphDatabaseService graphDatabaseService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        User vlad = new User();
        String dataRetrieved;
        Transaction tx = graphDatabaseService.beginTx();
        Node vladNode = graphDatabaseService.createNode();
        vladNode.setProperty("name","Vlad");
        dataRetrieved = vladNode.toString();
        tx.success();

        tx.close();


        return dataRetrieved;//"pong6";
    }
}
