package com.inviti.repository.config;


import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by vladyslavprytula on 8/6/14.
 */
@EnableTransactionManagement
@Configuration
@EnableNeo4jRepositories(basePackages = "com.inviti.repository")
public class DbConfig extends Neo4jConfiguration {
    @Resource
    private Environment env;

    public DbConfig() throws ClassNotFoundException {
        setBasePackage("com.inviti");
    }
    @Bean
    public GraphDatabaseService graphDatabaseService() {
        GraphDatabaseService graphDb= new SpringRestGraphDatabase(env.getProperty("inviti.restgraphdb.url"));
        return  graphDb;
    }
}