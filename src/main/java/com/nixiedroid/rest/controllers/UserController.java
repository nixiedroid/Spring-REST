package com.nixiedroid.rest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import com.nixiedroid.rest.dto.UserDTO;
import com.nixiedroid.rest.models.User;
import com.nixiedroid.rest.services.UserSvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    @GetMapping("/{uuid}")
    Optional<UserDTO> getUserByUuid(@PathVariable String uuid) {
        return svc.findByUUID(convertToUUID(uuid));
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
    @PutMapping("/{uuid}")
    ResponseEntity<UserDTO> putUser(@PathVariable String uuid, @RequestBody UserDTO User) {
        if (svc.existsByUUID(convertToUUID(uuid))) {
            return new ResponseEntity<>(svc.save(User), HttpStatus.OK);
        } else return new ResponseEntity<>(svc.save(User), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/Users/{id}">/Users/{id}</a>
     * and deletes User object if {id} found
     */

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteUser(@PathVariable String  uuid) {
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
