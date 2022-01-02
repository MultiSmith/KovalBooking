package com.kovalbooking.servicehotel.repo;

import com.kovalbooking.servicehotel.repo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

}
