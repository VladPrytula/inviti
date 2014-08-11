package com.inviti.repository.graph;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.test.TestGraphDatabaseFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An example of unit testing with Neo4j.
 */
public class Neo4jBasicDocTest
{
    protected GraphDatabaseService graphDb;

    /**
     * Create temporary database for each unit test.
     */
    @Before
    public void prepareTestDatabase()
    {
        graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
    }

    /**
     * Shutdown the database.
     */
    @After
    public void destroyTestDatabase()
    {
        graphDb.shutdown();
    }

    // in case we want to specify the setup
    @Test
    public void startWithConfiguration()
    {
        GraphDatabaseService db = new TestGraphDatabaseFactory()
                .newImpermanentDatabaseBuilder()
                .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
                .setConfig( GraphDatabaseSettings.string_block_size, "60" )
                .setConfig( GraphDatabaseSettings.array_block_size, "300" )
                .newGraphDatabase();
        db.shutdown();
    }

    @Test
    public void shouldCreateNode()
    {
        Node n = null;
        try ( Transaction tx = graphDb.beginTx() )
        {
            n = graphDb.createNode();
            n.setProperty( "name", "Nancy" );
            tx.success();
        }

        // The node should have a valid id
        assertThat( n.getId(), is( greaterThan( -1L ) ) );

        // Retrieve a node by using the id of the created node. The id's and
        // property should match.
        try ( Transaction tx = graphDb.beginTx() )
        {
            Node foundNode = graphDb.getNodeById( n.getId() );
            assertThat( foundNode.getId(), is( n.getId() ) );
            assertThat( (String) foundNode.getProperty( "name" ), is( "Nancy" ) );
        }
    }
}