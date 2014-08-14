package com.inviti.repository.graph.TestConfig;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@EnableTransactionManagement
@Configuration
@EnableNeo4jRepositories(basePackages = "com.inviti.repository")
public class TestDbConfig  extends Neo4jConfiguration {

    public TestDbConfig() throws ClassNotFoundException {
        setBasePackage("com.inviti");
    }
    @Bean
    public GraphDatabaseService graphDatabaseService() {
        GraphDatabaseService impermanentDb = new TestGraphDatabaseFactory()
                .newImpermanentDatabaseBuilder()
                .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
                .setConfig( GraphDatabaseSettings.string_block_size, "60" )
                .setConfig( GraphDatabaseSettings.array_block_size, "300" )
                .newGraphDatabase();
        return  impermanentDb;
    }

}
