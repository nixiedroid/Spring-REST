package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(RootController.class)
@Import(SecurityConfig.class)
public class RootControllerTest {

    private final MockMvc mvc;

    @Autowired
    public RootControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    public void testHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
