package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "site")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Generated
    @ColumnDefault("now()")
    @Column(name = "created")
    private Instant created;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "likedBy",fetch = FetchType.EAGER  )
    private Set<Coffee> favCoffees = new LinkedHashSet<>();

}