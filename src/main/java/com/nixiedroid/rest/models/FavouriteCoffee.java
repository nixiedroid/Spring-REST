package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favourite_coffees", schema = "site")
public class FavouriteCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favourite_coffees_id_gen")
    @SequenceGenerator(name = "favourite_coffees_id_gen", sequenceName = "favourite_coffees_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

}