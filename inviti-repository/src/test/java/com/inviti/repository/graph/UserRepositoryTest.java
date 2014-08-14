package com.inviti.repository.graph;

import com.inviti.model.Meeting;
import com.inviti.model.User;
import com.inviti.repository.graph.TestConfig.TestDbConfig;
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
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by vladyslavprytula on 8/12/14.
 */
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
    public void userFriendshipTest(){
        User user1 = new User("user1","1");
        User user2 = new User("user2","2");
        User user3 = new User("user3","3");

        User defaultUser = new User();
        userRepository.save(defaultUser);
        userRepository.findByUserName("default");
        assertThat( userRepository.findByUserName("default").iterator().next().getUserName(), is( "default" ) );
        userRepository.deleteAll();


        user1.setFamiliarUsers(new HashSet<>(Arrays.asList(user2, user3)));
        user2.setFamiliarUsers(new HashSet<>(Arrays.asList(user1,user3)));
        user3.setFamiliarUsers(new HashSet<>(Arrays.asList(user2,user1)));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<User> friendsOfUser1 = userRepository.findFriends("user1");
        assertThat(friendsOfUser1.get(0).getUserName(), isIn(new String[]{"user2", "user3"}));
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void userShouldBelongToMeetingTest(){

        User user1 = new User("user1","1");
        Meeting meeting = new Meeting();
        user1.belongsTo(meeting,"1stTestUser");

        meetingRepository.save(meeting);
        userRepository.save(user1);

        user1.getMemberships().iterator().next();
        meeting.getMeetingName();
        userRepository.findByMeeting("defaultMeeting");
        assertThat(userRepository.findByMeeting(meeting).iterator().next(), is(user1)) ;
        assertThat(userRepository.findByMeeting("defaultMeeting").iterator().next().getUserName(), is("user1"));
    }

    @Test
    @Transactional
    public void shoudlFindAllCollaboratorsThroughAllMeetingsTest(){
        User user11 = new User("user11","11Id");
        User user12 = new User("user12","12Id");
        User user13 = new User("user13","13Id");
        User user21 = new User("user21","21Id");
        User user22 = new User("user22","22Id");
        User user23 = new User("user23","23Id");

        Meeting meeting1 = new Meeting("meeting1","meeting1Id");
        Meeting meeting2 = new Meeting("meeting2","meeting2Id");

        user11.belongsTo(meeting1,"organizer");
        user12.belongsTo(meeting1,"casual");
        user13.belongsTo(meeting1,"casual");
        user21.belongsTo(meeting2,"casual");
        user22.belongsTo(meeting2,"casual");
        user23.belongsTo(meeting2,"organizer");
        user23.belongsTo(meeting1,"casual");


        meetingRepository.save(Arrays.asList(new Meeting[]{meeting1,meeting2}));
        userRepository.save(Arrays.asList(new User[]{user11, user12, user13, user21, user22, user23}));

        assertThat(userRepository.findByUserName("user11").iterator().next(), is(user11));
        assertThat(userRepository.findCollaborators(user23.getUserName()), containsInAnyOrder(new User[]{user11, user12, user13, user21, user22}));
    }

}
