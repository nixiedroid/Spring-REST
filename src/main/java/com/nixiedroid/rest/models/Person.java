package com.nixiedroid.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    int person_id;

    String first_name;
    String last_name;

}
