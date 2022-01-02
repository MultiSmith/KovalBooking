package com.kovalbooking.servicehotel.api;

import com.kovalbooking.servicehotel.api.dto.FeedbackDTO;
import com.kovalbooking.servicehotel.api.dto.HotelDTO;
import com.kovalbooking.servicehotel.repo.model.Hotel;
import com.kovalbooking.servicehotel.repo.model.Room;
import com.kovalbooking.servicehotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public final class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> index() {
        final List<Hotel> hotels = hotelService.fetchAll();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{hotel_id}")
    public ResponseEntity<Hotel> showById(@PathVariable long hotel_id) {
        try {
            final Hotel hotel = hotelService.fetchById(hotel_id);
            return ResponseEntity.ok(hotel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{hotel_id}/feedbacks")
    public ResponseEntity<List> getFeedbacksByHotelId(@PathVariable long hotel_id) {
        try {
            final List feedbackDTO = hotelService.getFeedbacksByHotelId(hotel_id);

            return ResponseEntity.ok(feedbackDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HotelDTO hotel) {
        final String hotel_name = hotel.getHotel_name();
        final String address = hotel.getAddress();
        final String city = hotel.getCity();
        final String hotel_info = hotel.getHotel_info();
        final Set<Room> rooms = hotel.getRooms();
        final long hotel_id = hotelService.create(hotel_name, address, city, hotel_info, rooms);
        final String location = String.format("/hotels/%d", hotel_id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{hotel_id}")
    public ResponseEntity<Void> update(@PathVariable long hotel_id, @RequestBody HotelDTO hotel) {
        final String hotel_name = hotel.getHotel_name();
        final String address = hotel.getAddress();
        final String city = hotel.getCity();
        final String hotel_info = hotel.getHotel_info();
        final Set<Room> rooms = hotel.getRooms();

        try {
            hotelService.update(hotel_id, hotel_name, address, city, hotel_info, rooms);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<Void> delete(@PathVariable long hotel_id) {
        hotelService.delete(hotel_id);
        return ResponseEntity.noContent().build();
    }
}
