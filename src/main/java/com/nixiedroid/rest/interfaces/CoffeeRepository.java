package com.nixiedroid.rest.interfaces;

import com.nixiedroid.rest.models.Coffee;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository Accessor for Spring JPA binding
 * Where Coffee is item, and Integer is key
 * CRUD Repository provides
 * Create - Read - Update - Delete operations.
 * Can easily be mapped onto REST Api idea.
 */
public interface CoffeeRepository extends ListCrudRepository<Coffee,Long> {
        Optional<Coffee> findDistinctByUuid(UUID uuid);
        boolean existsByUuid(UUID uuid);
        void deleteByUuid(UUID uuid);
}
