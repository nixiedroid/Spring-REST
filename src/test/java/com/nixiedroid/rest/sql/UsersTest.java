package com.nixiedroid.rest.sql;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.Coffee;
import com.nixiedroid.rest.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
@org.springframework.transaction.annotation.Transactional
public class UsersTest {

    private final UserRepository uR;
    private final CoffeeRepository cR;

    @Autowired
    public UsersTest(UserRepository uR, CoffeeRepository cR) {
        this.uR = uR;
        this.cR = cR;
    }

    @Test
    //@Transactional
   // @Rollback(value = false)
    public void checkUserAndCoffeeM2M() {
        Coffee c1 = new Coffee();
        c1.setName("TestCoffee1");
        c1.setHasMilk(false);
        cR.save(c1);

        Coffee c2 = new Coffee();
        c2.setName("TestCoffee2");
        c2.setHasMilk(false);
        cR.save(c2);

        User user1 = new User();
        user1.setFirstName("Test1");
        user1.setLastName("Test1");
        uR.save(user1);

        User user2 = new User();
        user2.setFirstName("Test2");
        user2.setLastName("Test2");
        uR.save(user2);

        c1.setLikedBy(Set.of(user1, user2));
        c2.setLikedBy(Set.of(user1));

        cR.save(c1);
        cR.save(c2);
        user1 = uR.findById(user1.getId()).orElse(null);
        user2 = uR.findById(user2.getId()).orElse(null);

        Assertions.assertNotNull(user1);
        Assertions.assertNotNull(user2);

        Assertions.assertEquals(Set.of(user1, user2),c1.getLikedBy());
        Assertions.assertEquals(Set.of(user1),c2.getLikedBy());
        Assertions.assertEquals(Set.of(c1, c2),user1.getFavCoffees());
        Assertions.assertEquals(Set.of(c1),user2.getFavCoffees());
    }
}
