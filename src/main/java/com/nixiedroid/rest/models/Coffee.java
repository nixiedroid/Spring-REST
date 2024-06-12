package com.nixiedroid.rest.models;

//was javax*
import jakarta.persistence.Entity;
//was javax*
import jakarta.persistence.Id;
import lombok.*;



/**
 * Entity for database Access
 */

//To avoid "no delegate- or property-based Creator" error during deserialization
//And for proper JPA configuration
@NoArgsConstructor

//Boilerplate
@Getter
@Setter
@AllArgsConstructor
@Entity
@EqualsAndHashCode

public class Coffee {

    /**
     * Unique coffee id
     */
    @Id
    private int id;
    /**
     * Coffee name
     */
    private  String name;

}
