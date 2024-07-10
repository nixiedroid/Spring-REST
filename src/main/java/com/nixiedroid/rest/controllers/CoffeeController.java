package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.dto.CoffeeDTO;
import com.nixiedroid.rest.models.Coffee;
import com.nixiedroid.rest.services.CoffeeSvc;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    @GetMapping("/{uuid}")
    Optional<CoffeeDTO> getCoffeeByUuid(@PathVariable String  uuid) {
        return svc.findByUUID(convertToUUID(uuid));
    }

    /**
     * Listens for POST requests at <a href="/coffees">/coffees</a>
     * and creates coffee object accordingly
     *
     * @return newly created json object {@link Coffee} on success
     */
    @PostMapping
    CoffeeDTO addCoffee(@Valid @RequestBody CoffeeDTO coffee) {
        return svc.save(coffee);
    }


    /**
     * Listens for PUT requests at <a href="/coffees{id}">/coffees/{id}</a>
     * and updates coffee object if {id} found
     * or creates coffee object if not-exists
     *
     * @return newly created json object {@link Coffee} on success
     */
    @PutMapping("/{uuid}")
    ResponseEntity<CoffeeDTO> putCoffee(@PathVariable String uuid, @RequestBody CoffeeDTO coffee) {
        if (svc.existsByUUID(convertToUUID(uuid))) {
            return new ResponseEntity<>(svc.save(coffee), HttpStatus.OK);
        } else return new ResponseEntity<>(svc.save(coffee), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/coffees/{id}">/coffees/{id}</a>
     * and deletes coffee object if {id} found
     */

    @DeleteMapping("/{uuid}")
    ResponseEntity<Void> deleteCoffee(@PathVariable String uuid) {
        if (svc.existsByUUID(convertToUUID(uuid))) {
            svc.deleteByUUID(convertToUUID(uuid));
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    private static final UUID EMPTY_UUID = new UUID(0,0);
    private UUID convertToUUID(String uuid){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException ignored){
            return EMPTY_UUID;
        }
    }
}
