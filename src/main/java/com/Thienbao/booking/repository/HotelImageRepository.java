package com.Thienbao.booking.repository;


import com.Thienbao.booking.entity.Hotel;
import com.Thienbao.booking.entity.HotelImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage,Integer> {
    List<HotelImage> findByHotelId(Hotel hotelsEntity);
}
