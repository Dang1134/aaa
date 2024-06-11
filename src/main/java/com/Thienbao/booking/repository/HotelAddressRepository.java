package com.Thienbao.booking.repository;


import com.Thienbao.booking.entity.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelAddressRepository  extends JpaRepository<HotelAddress,Integer> {
}
