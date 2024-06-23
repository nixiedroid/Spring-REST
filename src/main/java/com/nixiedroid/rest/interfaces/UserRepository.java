package com.nixiedroid.rest.interfaces;

import com.nixiedroid.rest.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {
    User findDistinctByFirstNameAndLastName(String firstName, String lastName);
}
