package com.nixiedroid.rest.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

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
@Table(name = "coffees", schema = "site")
public class Coffee {

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private final Coffee THIS = this;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
    @Setter(AccessLevel.NONE)
    private Instant created;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
    },fetch = FetchType.LAZY)
    @JoinTable(schema = "site", name = "favourite_coffees", joinColumns = @JoinColumn(name = "coffee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<User> likedBy = new HashSet<>();


    void addLikedBy(User u) {
        this.likedBy.add(u);
    }

    public void addLikedByAll(Collection<? extends User> us) {
        this.likedBy.addAll(us);
        us.forEach(u-> u.addFavCoffee(THIS));
    }

    public Set<User> getLikedBy() {
        return Collections.unmodifiableSet(likedBy);
    }

}