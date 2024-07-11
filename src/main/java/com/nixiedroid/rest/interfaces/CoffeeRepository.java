package com.nixiedroid.rest.interfaces;

import com.nixiedroid.rest.models.Coffee;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.lang.Long;

/**
 * Repository Accessor for Spring JPA binding
 * Where Coffee is item, and Integer is key
 * CRUD Repository provides
 * Create - Read - Update - Delete operations.
 * Can easily be mapped onto REST Api idea.
 */
public interface CoffeeRepository extends ListCrudRepository<Coffee,Long> {
        Optional<Coffee> findDistinctById(Long id);
        boolean existsById(Long id);
        void deleteById(Long id);
}
