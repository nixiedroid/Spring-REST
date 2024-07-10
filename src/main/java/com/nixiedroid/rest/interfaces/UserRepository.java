package com.nixiedroid.rest.interfaces;

import com.nixiedroid.rest.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {
    Optional<User> findDistinctByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
