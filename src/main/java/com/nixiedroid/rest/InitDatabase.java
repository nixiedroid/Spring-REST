package com.nixiedroid.rest;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Holder for One-time run code for database initial setup
 */
@Component
public class InitDatabase {

    //Field injection is not recommended
    //@Autowired
    private final CoffeeRepository coffeeRepository;

    /**
     *
     * @param coffeeRepository is autowired by Spring
     */
    @Autowired
    public InitDatabase(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
        System.out.println(coffeeRepository.getClass().getSimpleName());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initRoutine() {
        System.out.println("Init routine");
        System.out.println(org.springframework.http.HttpStatus.valueOf(404).name());
        this.coffeeRepository.saveAll(List.of(
                new Coffee(1, "Americano"),
                new Coffee(2, "Latte"),
                new Coffee(3, "Cappuccino")
        ));
    }

}
