package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.dto.CoffeeDTO;
import com.nixiedroid.rest.models.Coffee;
import com.nixiedroid.rest.services.CoffeeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for  <a href="/coffees">/coffees</a> endpoint
 *
 * @see Coffee
 * @see CoffeeDTO
 */
@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    CoffeeSvc svc;

    @Autowired
    CoffeeController(CoffeeSvc svc) {
        this.svc = svc;
    }

    /**
     * Listens for GET requests at <a href="/coffees">/coffees</a>
     *
     * @return json list of {@link Coffee}
     */
    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping
    Iterable<CoffeeDTO> getCoffees() {
        return svc.findAll();
    }

    /**
     * Listens for GET requests at <a href="/coffees{id}">/coffees/{id}</a>
     *
     * @return json object {@link Coffee} if {id} exists or null
     */
    @GetMapping("/{id}")
    Optional<CoffeeDTO> getCoffeeById(@PathVariable int id) {
        return svc.findById(id);
    }

    /**
     * Listens for POST requests at <a href="/coffees">/coffees</a>
     * and creates coffee object accordingly
     *
     * @return newly created json object {@link Coffee} on success
     */
    @PostMapping
    CoffeeDTO addCoffee(@RequestBody CoffeeDTO coffee) {
        return svc.save(coffee);
    }


    /**
     * Listens for PUT requests at <a href="/coffees{id}">/coffees/{id}</a>
     * and updates coffee object if {id} found
     * or creates coffee object if not-exists
     *
     * @return newly created json object {@link Coffee} on success
     */
    @PutMapping("/{id}")
    ResponseEntity<CoffeeDTO> putCoffee(@PathVariable int id, @RequestBody CoffeeDTO coffee) {
        if (svc.existsById(id)) {
            return new ResponseEntity<>(svc.save(coffee), HttpStatus.OK);
        } else return new ResponseEntity<>(svc.save(coffee), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/coffees/{id}">/coffees/{id}</a>
     * and deletes coffee object if {id} found
     */

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable int id) {
        svc.deleteById(id);
    }

}
