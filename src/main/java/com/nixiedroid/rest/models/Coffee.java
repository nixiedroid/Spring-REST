package com.nixiedroid.rest.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(schema = "site", name = "favourite_coffees", joinColumns = @JoinColumn(name = "coffee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<User> likedBy = new HashSet<>();


    void addLikedBy0(User u) {
        this.likedBy.add(u);
    }

    void removeLikedBy0(User u) {
        this.likedBy.remove(u);
    }

    public void addLikedByAll(Collection<? extends User> us) {
        this.likedBy.addAll(us);
        us.forEach(u-> u.addFavCoffee0(THIS));
    }

    public Set<User> getLikedBy() {
        return Collections.unmodifiableSet(likedBy);
    }

    public void addLikedBy(User u) {
        this.likedBy.add(u);
        u.addFavCoffee0(THIS);
    }

    public void removeLikedBy(User u) {
        this.likedBy.remove(u);
        u.removeFavCoffee0(THIS);
    }
}