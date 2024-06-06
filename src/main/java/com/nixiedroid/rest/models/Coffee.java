package com.nixiedroid.rest.models;

import java.util.Objects;
import java.util.Random;

public class Coffee {
    //Unique id
    private final int id;

    //Name of coffee
    private  String name;

    //Coffee constructor. Id is generated automatically
    public Coffee(String name) {
        this.id = generateId();
        this.name = name;
    }

    /**
     * Generates coffee id
     * 0xC0FE0000 + random short
     * @return
     */
    private static int generateId(){
        return  0xC0FE0000 | ( new Random().nextInt() & 0xFFFF );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coffee coffee = (Coffee) o;
        return id == coffee.id && Objects.equals(name, coffee.name);
    }

    @Override
    public int hashCode() {
        int hash = id;
        hash = 31 * hash + Objects.hashCode(name);
        return hash;
    }
}
