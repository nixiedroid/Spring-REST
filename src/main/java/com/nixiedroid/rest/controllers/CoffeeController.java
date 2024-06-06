package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.models.Coffee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CoffeeController {
    private final List<Coffee > coffees = new ArrayList<>();

    public CoffeeController() {
       coffees.addAll(List.of(
               new Coffee("Americano"),
               new Coffee("Latte"),
               new Coffee("Cappuccino")
       ));
    }
    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping("/coffees")
    List<Coffee> getCoffees(){
     return coffees;
    }
    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable int id ){
        for (Coffee coffee: coffees){
            if (id == coffee.getId()) return Optional.of(coffee);
        }
        return Optional.empty();
    }
}
