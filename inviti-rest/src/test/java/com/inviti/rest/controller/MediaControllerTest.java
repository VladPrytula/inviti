package com.inviti.rest.controller;

import com.inviti.rest.config.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class MediaControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testProvideUploadInfo() throws Exception {
    }

    @Test
    public void testHandleFileUpload() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/media/upload")
                .file(new MockMultipartFile("file", "orig", null, "bar".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}