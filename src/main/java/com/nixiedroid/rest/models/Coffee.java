package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "coffees", schema = "site")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "coffees_id_gen", sequenceName = "coffee_list_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ColumnDefault("false")
    @Column(name = "has_milk")
    private Boolean hasMilk;

    @Generated
    @ColumnDefault("now()")
    @Column(name = "created")
    private Instant created;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            schema = "site",
            name = "favourite_coffees",
            joinColumns = @JoinColumn(name = "coffee_id",referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName="id")
    )
    private Set<User> likedBy;
}