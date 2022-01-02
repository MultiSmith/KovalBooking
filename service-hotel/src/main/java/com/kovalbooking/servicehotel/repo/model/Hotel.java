package com.kovalbooking.servicehotel.repo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public final class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotel_id;
    private String hotel_name;
    private String address;
    private String city;
    private String hotel_info;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="hotel_rooms", joinColumns = @JoinColumn(name="hotel_id"),inverseJoinColumns = @JoinColumn(name="room_id"))
    private Set<Room> rooms;

    public Hotel(String hotel_name, String address, String city, String hotel_info, Set<Room> rooms) {
        this.hotel_name = hotel_name;
        this.address = address;
        this.city = city;
        this.hotel_info = hotel_info;
        this.rooms = rooms;
    }

    public Hotel() {
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotel_info() {
        return hotel_info;
    }

    public void setHotel_info(String hotel_info) {
        this.hotel_info = hotel_info;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
