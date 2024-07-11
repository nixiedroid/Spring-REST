package com.nixiedroid.rest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import com.nixiedroid.rest.dto.UserDTO;
import com.nixiedroid.rest.models.User;
import com.nixiedroid.rest.services.UserSvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.lang.Long;

/**
 * Controller class for  <a href="/Users">/Users</a> endpoint
 *
 * @see User
 * @see UserDTO
 */
@RestController
@RequestMapping("/users")
public class UserController {

    UserSvc svc;

    @Autowired
    UserController(UserSvc svc) {
        this.svc = svc;
    }

    /**
     * Listens for GET requests at <a href="/Users">/Users</a>
     *
     * @return json list of {@link User}
     */
    //@RequestMapping(value = "/Users", method = RequestMethod.GET)
    @GetMapping
    Iterable<UserDTO> getUsers() {
        return svc.findAll();
    }

    /**
     * Listens for GET requests at <a href="/Users{id}">/Users/{id}</a>
     *
     * @return json object {@link User} if {id} exists or null
     */
    @GetMapping("/{id}")
    Optional<UserDTO> getUserById(@PathVariable Long id) {
        return svc.findById(id);
    }

    /**
     * Listens for POST requests at <a href="/Users">/Users</a>
     * and creates User object accordingly
     *
     * @return newly created json object {@link User} on success
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO addUser(@RequestBody UserDTO User) {
        return svc.save(User);
    }


    /**
     * Listens for PUT requests at <a href="/Users{id}">/Users/{id}</a>
     * and updates User object if {id} found
     * or creates User object if not-exists
     *
     * @return newly created json object {@link User} on success
     */
    @PutMapping("/{id}")
    ResponseEntity<UserDTO> putUser(@PathVariable Long id, @RequestBody UserDTO User) {
        if (svc.existsById(id)) {
            return new ResponseEntity<>(svc.save(User), HttpStatus.OK);
        } else return new ResponseEntity<>(svc.save(User), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/Users/{id}">/Users/{id}</a>
     * and deletes User object if {id} found
     */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteUser(@PathVariable Long  id) {
        if (svc.existsById(id)) {
            svc.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
}
