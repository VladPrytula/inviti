package com.inviti.rest.controller;

import com.inviti.model.domainmodel.User;
import com.inviti.rest.config.TestContext;
import com.inviti.service.userservice.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;


/**
 * Created by vladyslavprytula on 8/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class UserControllerTestIT {

    @Resource
    WebApplicationContext wac;


    @Mock
    UserService userServiceMock;

    @Mock
    User userMock;

    @InjectMocks
    UserController userController ;

    private MockMvc mockMvc;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void basicUserControllerTest() throws Exception {
        Mockito.doNothing().when(userServiceMock).save(Mockito.any(User.class));
        Mockito.when(userMock.getUserName()).thenReturn("user12345");
        Mockito.when(userServiceMock.find("user12345")).thenReturn(userMock);


        this.mockMvc.perform(MockMvcRequestBuilders.get("/ping")
                .accept(MediaType.TEXT_HTML))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("user12345"));
    }

}
