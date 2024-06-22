package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "coffees", schema = "site")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffees_id_gen")
    @SequenceGenerator(name = "coffees_id_gen", sequenceName = "coffee_list_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ColumnDefault("false")
    @Column(name = "has_milk")
    private Boolean hasMilk;

    @ColumnDefault("now()")
    @Column(name = "created")
    private Instant created;

}