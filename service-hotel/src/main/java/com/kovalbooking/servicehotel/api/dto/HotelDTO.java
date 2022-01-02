package com.kovalbooking.servicehotel.api.dto;

import com.kovalbooking.servicehotel.repo.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class HotelDTO {
    private String hotel_name;
    private String address;
    private String city;
    private String hotel_info;
    private Set<Room> rooms;
}
