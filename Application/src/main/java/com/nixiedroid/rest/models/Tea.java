package com.nixiedroid.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Entity for database Access
 */

//To avoid "no delegate- or property-based Creator" error during deserialisation
//And for proper JPA configuration
@NoArgsConstructor

//Boilerplate
@Getter
@Setter
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Tea {
    /**
     * Unique tea id
     */
    @Id
    private int id;
    /**
     * Tea name
     */
    private  String name;
}
