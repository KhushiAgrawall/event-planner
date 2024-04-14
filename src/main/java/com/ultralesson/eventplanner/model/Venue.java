package com.ultralesson.eventplanner.model;

import com.ultralesson.eventplanner.exceptions.VenueException;

import java.util.Objects;

public class Venue {
    private int id;
    private String name;
    private String address;
    private int capacity;

    public Venue(int id, String name, String address, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        if(name.isEmpty()||address.isEmpty()){
            throw new VenueException("Incomplete details");
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return id == venue.id &&
                capacity == venue.capacity &&
                Objects.equals(name, venue.name) &&
                Objects.equals(address, venue.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, capacity);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

