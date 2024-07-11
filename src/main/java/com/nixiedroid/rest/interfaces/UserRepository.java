package com.nixiedroid.rest.interfaces;

import com.nixiedroid.rest.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.lang.Long;

@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {
    Optional<User> findDistinctById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
