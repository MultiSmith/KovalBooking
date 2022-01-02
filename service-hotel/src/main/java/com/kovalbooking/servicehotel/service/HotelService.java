package com.kovalbooking.servicehotel.service;

import com.kovalbooking.servicehotel.api.dto.FeedbackDTO;
import com.kovalbooking.servicehotel.repo.HotelRepo;
import com.kovalbooking.servicehotel.repo.model.Hotel;
import com.kovalbooking.servicehotel.repo.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public final class HotelService {
    private final HotelRepo hotelRepo;
    private final String feedbacksURL = "http://service-feedback:8081/feedbacks/";

    public List<Hotel> fetchAll() {
        return hotelRepo.findAll();
    }

    public Hotel fetchById(long id) throws IllegalArgumentException {
        final Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isEmpty()) throw new IllegalArgumentException("No hotels found!");
        else return optionalHotel.get();
    }

    public long create(String hotel_name, String address, String city, String hotel_info, Set<Room> rooms) {
        final Hotel hotel = new Hotel(hotel_name, address, city, hotel_info, rooms);
        final Hotel saved = hotelRepo.save(hotel);
        return saved.getHotel_id();
    }

    public void update(long id, String hotel_name, String address, String city, String hotel_info, Set<Room> rooms) throws IllegalArgumentException {
        final Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isEmpty()) throw new IllegalArgumentException("No hotels found!");

        final Hotel hotel = optionalHotel.get();
        if(hotel_name != null && !hotel_name.isBlank()) hotel.setHotel_name(hotel_name);
        if(address != null && !address.isBlank()) hotel.setAddress(address);
        if(city != null && !city.isBlank()) hotel.setCity(city);
        if(hotel_info != null && !hotel_info.isBlank()) hotel.setHotel_info(hotel_info);
        if(rooms != null && !rooms.isEmpty()) hotel.setRooms(rooms);

        hotelRepo.save(hotel);
    }

    public void delete(long id) throws IllegalArgumentException {
        hotelRepo.deleteById(id);
    }

    public List<FeedbackDTO> getFeedbacksByHotelId(long hotel_id) {
        final Optional<Hotel> optionalHotel = hotelRepo.findById(hotel_id);
        if (optionalHotel.isEmpty()) throw new IllegalArgumentException("No hotels found!");

        final RestTemplate restTemplate = new RestTemplate();
        //final ResponseEntity<Object[]> response = restTemplate.getForEntity(feedbacksURL, Object[].class);
        final ResponseEntity<List<FeedbackDTO>> response = restTemplate.exchange(feedbacksURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<FeedbackDTO>>() {});
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) throw new IllegalArgumentException("No user found!");

        final Hotel hotel = optionalHotel.get();
        List<FeedbackDTO> hotelFeedbacks = response.getBody();
        return hotelFeedbacks.stream().filter(element -> element.getHotel_id() == hotel_id).collect(Collectors.toList());
    }
}
