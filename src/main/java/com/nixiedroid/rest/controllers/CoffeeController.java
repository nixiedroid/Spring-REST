package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for  <a href="/coffees">/coffees</a> endpoint
 *
 * @see com.nixiedroid.rest.models.Coffee
 */
@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    /**
     * Coffee repository accessor
     */
    private final CoffeeRepository coffeeRepository;


    /**
     * Coffee controller constructor
     *
     * @param coffeeRepository is autowired by Spring
     */

    //Autowired annotation can be omitted since Spring v4.3
    @Autowired
    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    /**
     * Listens for GET requests at <a href="/coffees">/coffees</a>
     *
     * @return json list of {@link com.nixiedroid.rest.models.Coffee}
     */

    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    /**
     * Listens for GET requests at <a href="/coffees{id}">/coffees/{id}</a>
     *
     * @return json object {@link com.nixiedroid.rest.models.Coffee} if {id} exists or null
     */
    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable int id) {
        return coffeeRepository.findById(id);
    }

    /**
     * Listens for POST requests at <a href="/coffees">/coffees</a>
     * and creates coffee object accordingly
     *
     * @return newly created json object {@link com.nixiedroid.rest.models.Coffee} on success
     */
    @PostMapping
    Coffee addCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }


    /**
     * Listens for PUT requests at <a href="/coffees{id}">/coffees/{id}</a>
     * and updates coffee object if {id} found
     * or creates coffee object if not-exists
     *
     * @return newly created json object {@link com.nixiedroid.rest.models.Coffee} on success
     */
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable int id, @RequestBody Coffee coffee) {
        if (coffeeRepository.existsById(id)) {
            return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
        } else return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/coffees/{id}">/coffees/{id}</a>
     * and deletes coffee object if {id} found
     */

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable int id) {
        coffeeRepository.deleteById(id);
    }

}
