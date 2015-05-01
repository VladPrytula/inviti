package com.inviti.repository.graph;

import com.inviti.model.domainmodel.Meeting;
import com.inviti.model.domainmodel.User;
import com.inviti.repository.annotations.ProductionConfig;
import com.inviti.repository.config.DbConfig;
import com.inviti.repository.config.PropertiesConfig;
import com.inviti.repository.config.TestDbConfig;
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

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MeetingRepository meetingRepository;

    @Before
    public void cleanData(){
        userRepository.deleteAll();
        meetingRepository.deleteAll();
    }

    @Test
    @Transactional
    public void basicUserSearchTest(){
        //perform basic search test
        User defaultUser = new User();
        userRepository.save(defaultUser);
        userRepository.findByUserName("defaultUserName");
        assertThat( userRepository.findByUserName("defaultUserName").iterator().next().getUserName(), is( "defaultUserName" ) );
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void userFriendshipTest(){

        //Define several users
        User user1 = new User("1","user1");
        User user2 = new User("2","user2");
        User user3 = new User("3","user3");

        // persistence order is important for neo
        userRepository.save(user2);

        //check knowsOf relationship
        user1.knowsOf(user2,new Date().getTime(), new Date().getTime() + 1000000000l);

        userRepository.save(user3);
        user1.knowsOf(user3,new Date().getTime(), new Date().getTime() + 1000000000l);

        userRepository.save(user1);
        user3.knowsOf(user1,new Date().getTime(), new Date().getTime() + 1000000000l);



        List<User> friendsOfUser1 = userRepository.findFriends("user1");
        assertThat(friendsOfUser1.get(0).getUserName(), isIn(new String[]{"user2", "user3"}));
    }

    @Test
    @Transactional
    public void userShouldBelongToMeetingTest(){

        User user1 = new User("1","user1");
        Meeting meeting = new Meeting();
        user1.belongsTo(meeting,"1stTestUse_role",new Date().getTime(), new Date().getTime() + 1000000000l );

        meetingRepository.save(meeting);
        userRepository.save(user1);

        meeting.getMeetingName();
        Set<Meeting> userSet = meetingRepository.getMeetings(user1.getUserName());
        assertThat(meetingRepository.getMeetings(user1.getUserName()).iterator().next(), is(meeting)) ;
    }
}
