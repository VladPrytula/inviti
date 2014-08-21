package com.inviti.repository.graph;

import com.inviti.model.identity.MeetingIdentityNode;
import com.inviti.model.identity.UserIdentityNode;
import com.inviti.model.state.MeetingStateNode;
import com.inviti.model.state.UserStateNode;
import com.inviti.repository.config.DbConfig;
import com.inviti.repository.config.PropertiesConfig;
import com.inviti.repository.graph.State.MeetingStateRepository;
import com.inviti.repository.graph.State.UserStateRepository;
import com.inviti.repository.graph.identity.MeetingIdentityRepository;
import com.inviti.repository.graph.identity.UserIdentityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by vladyslavprytula on 8/20/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class, PropertiesConfig.class})//{TestDbConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class HistoryGraphIntegrationTest {

    @Autowired
    UserIdentityRepository userIdentityRepository;
    @Autowired
    UserStateRepository userStateRepository;

    @Autowired
    MeetingIdentityRepository meetingIdentityRepository;
    @Autowired
    MeetingStateRepository meetingStateRepository;

    @Before
    public void cleanData(){
        userIdentityRepository.deleteAll();
        userIdentityRepository.deleteAll();
        meetingIdentityRepository.deleteAll();
        meetingStateRepository.deleteAll();
    }


    @Test
    @Transactional // TODO: ATTENTION: this has NO effect for REST remote DB. no equality in case of remote DB!
    public void shouldBuildAtomicInTimeGraph(){
        // To build an integral user structure we have to create identityNode and stateNode for user and tie them together with the respective time stamp
        UserIdentityNode userIdentityNode1 = new UserIdentityNode("userIdentityNode1");
        UserStateNode userStateNode1 = new UserStateNode("userStateNode1");
        userIdentityNode1.setNewState(userStateNode1, new Date().getTime(), new Date().getTime() + 1000000000l);


  /*      UserIdentityNode sameUserIdNode = userIdentityRepository.findAll().iterator().next();
        assertThat(userIdentityNode1, is(equalTo(sameUserIdNode)));*/

        // To build an integral meeting structure we have to create identityNode and stateNode for user and tie them together with the respective time stamp
        MeetingIdentityNode meetingIdentityNode = new MeetingIdentityNode("meetingIdentityNode");
        MeetingStateNode meetingStateNode = new MeetingStateNode("meetingStateNode");
        meetingIdentityNode.setNewState(meetingStateNode, new Date().getTime(), new Date().getTime() + 1000000000l);

        //now we have to tie the userId to the meetingId and set validity interval of the relationship
        userIdentityNode1.belongsTo(meetingIdentityNode, "organizer", new Date().getTime(), new Date().getTime() + 1000000000l);

        /**persist data to graph DB
         * TODO: the case here is that our mapping framework does not handle cascading saves :(.
         * Perphas, I have to implement cascade save of the dependent objects (i.e. *id is dependent on *state) based on TransactionEventHandler<T>
         * The oder is very important.
         *
         * TODO: To discuss:
         * simple mapping always detaches entities on load as it copies the data out of the graph into the entities and stores it back fully too.
         * I was not able to bootstrap AspectJ-backend for spring-data with our configuration.
         * Perhaps it would be better to do via persist and advanced mode. so that we could have something lik User user = new User().persist();
         * additionally we would get cascading save.
        **/
        userStateRepository.save(userStateNode1);
        meetingStateRepository.save(meetingStateNode);
        meetingIdentityRepository.save(meetingIdentityNode);
        userIdentityRepository.save(userIdentityNode1);

        assertThat(meetingStateRepository.findAll().iterator().next(), is(equalTo(meetingStateNode)));
    }


    @Test
    @Transactional
    public void shouldCreateHistoryState(){
        // To build an integral user structure we have to create identityNode and stateNode for user and tie them together with the respective time stamp
        UserIdentityNode userIdentityNode1 = new UserIdentityNode("userIdentityNode1");
        UserStateNode userStateNode11 = new UserStateNode("userStateNode11");
        userIdentityNode1.setNewState(userStateNode11, new Date().getTime(), new Date().getTime() + 1000000000l);


        UserIdentityNode userIdentityNode2 = new UserIdentityNode("userIdentityNode2");
        UserStateNode userStateNode21 = new UserStateNode("userStateNode21");
        userIdentityNode2.setNewState(userStateNode21, new Date().getTime(), new Date().getTime() + 1000000000l);

        //Create a new state for userIdentityNode1, set it with new time frame and set the userIdentityNode1 time frame bounded by the "same" time
        UserStateNode userStateNode12 = new UserStateNode("userStateNode12");
        userIdentityNode1.setNewState(userStateNode12, new Date().getTime(), new Date().getTime() + 10000000000l);

        // To build an integral meeting structure we have to create identityNode and stateNode for user and tie them together with the respective time stamp
        MeetingIdentityNode meetingIdentityNode = new MeetingIdentityNode("meetingIdentityNode");
        MeetingStateNode meetingStateNode = new MeetingStateNode("meetingStateNode");
        meetingIdentityNode.setNewState(meetingStateNode, new Date().getTime(), new Date().getTime() + 1000000000l);

        userIdentityNode1.belongsTo(meetingIdentityNode, "organzier", new Date().getTime(), new Date().getTime() + 1000000000l);

        //persist to DB
        userStateRepository.save(Arrays.asList( new UserStateNode[]{userStateNode11, userStateNode12,userStateNode21}));

        meetingStateRepository.save(meetingStateNode);
        meetingIdentityRepository.save(meetingIdentityNode);

        userIdentityRepository.save(Arrays.asList(new UserIdentityNode[]{userIdentityNode1, userIdentityNode2}));

    }
}
