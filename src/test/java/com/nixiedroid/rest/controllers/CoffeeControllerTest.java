package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;

@SpringBootTest
//@WebMvcTest(CoffeeController.class)
public class CoffeeControllerTest {

    private MockMvc mvc;
    private CoffeeRepository cR = mock(CoffeeRepository.class);



//    @Test
//    public void getCoffeeByIdTest() throws Exception {
//
//
//        mvc.perform(get("/coffees/1").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(
//                        "{\"id\":1,\"name\":\"Americano\"}"
//                ));
//    }
}

