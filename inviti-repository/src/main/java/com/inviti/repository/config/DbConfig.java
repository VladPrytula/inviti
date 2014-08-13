package com.inviti.repository.config;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

    public DbConfig() throws ClassNotFoundException {
        setBasePackage("com.inviti.model");
    }
    @Bean
    public GraphDatabaseService graphDatabaseService() {

        Path tempDir = null;
        try {
            tempDir = Files.createTempDirectory("test-neo4j");
        } catch (IOException e) {
            e.printStackTrace();
        }
       // GraphDatabaseService remoteGraphDb = new RestGraphDatabase("http://localhost:7474/db/data");
         GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(tempDir.toString());
        return  graphDb;
        //RestGraphDatabase("http://localhost:7474/db/data");
    }
}