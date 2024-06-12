package com.nixiedroid.rest.listeners;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Holder for One-time run code for initial setup
 */
@Component
public class InitListener {

    //Field injection is not recommended
    //@Autowired
    private final CoffeeRepository coffeeRepository;

    /**
     *
     * @param coffeeRepository is autowired by Spring
     */
    @Autowired
    public InitListener(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initRoutine() {
        this.coffeeRepository.saveAll(List.of(
                new Coffee(1, "Americanfo"),
                new Coffee(2, "Latte"),
                new Coffee(3, "Cappuccino")
        ));
    }

}
