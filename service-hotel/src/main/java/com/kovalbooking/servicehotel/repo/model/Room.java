package com.kovalbooking.servicehotel.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;
    private String room_name;
    private long area;
    private long num_beds;
    private String room_class;

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public long getNum_beds() {
        return num_beds;
    }

    public void setNum_beds(long num_beds) {
        this.num_beds = num_beds;
    }

    public String getRoom_class() {
        return room_class;
    }

    public void setRoom_class(String room_class) {
        this.room_class = room_class;
    }
}
