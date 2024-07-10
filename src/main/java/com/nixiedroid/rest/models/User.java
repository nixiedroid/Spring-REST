package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "users", schema = "site")
public class User {
    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private final User THIS = this;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Generated
    @ColumnDefault("now()")
    @Column(name = "created")
    @Setter(AccessLevel.NONE)
    private Instant created;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "likedBy", fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<Coffee> favCoffees = new HashSet<>();

    @Column(name = "uid", nullable = false)
    private UUID uuid;


    void addFavCoffee0(Coffee c) {
        this.favCoffees.add(c);
    }

    public Set<Coffee> getFavCoffees() {
        return Collections.unmodifiableSet(favCoffees);
    }

    public void addFavCoffeeAll(Collection<? extends Coffee> cs) {
        this.favCoffees.addAll(cs);
        cs.forEach(c -> c.addLikedBy0(THIS));
    }

}