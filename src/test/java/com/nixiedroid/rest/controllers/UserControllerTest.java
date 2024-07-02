package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest()
class UserControllerTest {

    @Test
    void getUsers() {


        // Assertions.assertTrue();
      //  uC.getUserById(user1ID);


        User u1 = new User();
        u1.setFirstName("Test1FN");
        u1.setLastName("Test1LN");


    }

    @Test
    void getUserById() {
    }
}