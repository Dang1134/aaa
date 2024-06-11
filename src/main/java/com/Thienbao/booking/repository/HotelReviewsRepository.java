package com.Thienbao.booking.repository;

import com.Thienbao.booking.entity.Hotel;
import com.Thienbao.booking.entity.HotelReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReviewsRepository extends JpaRepository<HotelReviews,Integer> {
    List<HotelReviews> findByHotelId(Hotel hotelId);
}
