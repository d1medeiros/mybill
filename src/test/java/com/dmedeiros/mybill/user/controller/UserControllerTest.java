package com.dmedeiros.mybill.user.controller;

import com.dmedeiros.mybill.App;
import com.dmedeiros.mybill.user.model.User;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UserControllerTest {

    private MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    Charset.forName("utf8"));

    private MockMvc mockMvc;

    private User user;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User();
        user.setId(1l);
        user.setName("Diego");
        user.setLogin("aian");
        user.setPassword("1234");
    }

    private MockHttpServletRequestBuilder postMockHttpServletRequest(User user) {
        JSONObject userJson = new JSONObject(user);
        return MockMvcRequestBuilders.post("/user")
                .content(userJson.toString())
                .contentType(this.contentType);
    }

    @Test
    public void saveSimpleUser() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequest = postMockHttpServletRequest(user);
        mockMvc.perform(mockHttpServletRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}