package com.inviti.repository.graph;

import com.inviti.model.User;
import com.inviti.repository.graph.TestConfig.TestDbConfig;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;

/**
 * Created by vladyslavprytula on 8/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })

public class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void userFriendshipTest(){
        User user1 = new User("user1","1");
        User user2 = new User("user2","2");
        User user3 = new User("user3","3");
        userRepository.deleteAll();

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
}
