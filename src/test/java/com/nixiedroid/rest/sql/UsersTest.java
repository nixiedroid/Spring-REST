package com.nixiedroid.rest.sql;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.Coffee;
import com.nixiedroid.rest.models.Environment;
import com.nixiedroid.rest.models.Info;
import com.nixiedroid.rest.models.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@org.springframework.transaction.annotation.Transactional
public class UsersTest {

    static final long nxID = -1L;
    private final UserRepository uR;
    private final CoffeeRepository cR;
    private final Info info;

    @Autowired
    EntityManager entityManager;

    @Autowired
    public UsersTest(UserRepository uR, CoffeeRepository cR, Info info) {
        this.uR = uR;
        this.cR = cR;
        this.info = info;
    }

    @BeforeEach
    void checkEnv() {
        Assertions.assertEquals(info.getEnv(), Environment.TEST);
    }

    @Test
    void getUser() {
        User u1 = new User();
        u1.setFirstName("Test1FN");
        u1.setLastName("Test1LN");
        uR.save(u1);

        User u2 = new User();
        u2.setFirstName("Test2FN");
        u2.setLastName("Test2LN");
        uR.save(u2);

        Optional<User> u = uR.findById(nxID);
        Assertions.assertNotNull(u1.getId());
        Assertions.assertTrue(uR.findById(u1.getId()).isPresent());
        Assertions.assertEquals(uR.findById(u1.getId()).get().getFirstName(), u1.getFirstName());
        Assertions.assertEquals(uR.findById(u1.getId()).get().getLastName(), u1.getLastName());
        Assertions.assertEquals(uR.findById(u1.getId()).get().getFavCoffees(), u1.getFavCoffees());
        Assertions.assertNotNull(u2.getId());
        Assertions.assertTrue(uR.findById(u2.getId()).isPresent());
        Assertions.assertEquals(uR.findById(u2.getId()).get().getFirstName(), u2.getFirstName());
        Assertions.assertEquals(uR.findById(u2.getId()).get().getLastName(), u2.getLastName());
        Assertions.assertEquals(uR.findById(u2.getId()).get().getFavCoffees(), u2.getFavCoffees());
        Assertions.assertFalse(u.isPresent());

    }


    @Test
    public void checkUserAndCoffeeM2M() {
        Coffee c1 = new Coffee();
        c1.setName("TestCoffee1");
        c1.setHasMilk(false);
        c1 = cR.save(c1);

        Coffee c2 = new Coffee();
        c2.setName("TestCoffee2");
        c2.setHasMilk(false);
        c2 = cR.save(c2);

        User user1 = new User();
        user1.setFirstName("Test1");
        user1.setLastName("Test1");
        user1 = uR.save(user1);

        User user2 = new User();
        user2.setFirstName("Test2");
        user2.setLastName("Test2");
        user2 = uR.save(user2);

        //CrudRepository has only save but it acts as update as well.

        c1.addLikedByAll(Set.of(user1,user2));
        c2.addLikedByAll(Set.of(user1));
        cR.save(c1);
        cR.save(c2);

        user1 = uR.findById(user1.getId()).orElse(null);
        user2 = uR.findById(user2.getId()).orElse(null);

        Assertions.assertNotNull(user1);
        Assertions.assertNotNull(user2);

        Assertions.assertEquals(Set.of(user1, user2), c1.getLikedBy());
        Assertions.assertEquals(Set.of(user1), c2.getLikedBy());
        Assertions.assertEquals(Set.of(c1, c2), user1.getFavCoffees());
        Assertions.assertEquals(Set.of(c1), user2.getFavCoffees());
    }
}
