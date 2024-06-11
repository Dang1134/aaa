package com.Thienbao.booking.dto;

import com.Thienbao.booking.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class HotelDTO  {
    private int id;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private LocalTime closeTime;
    private LocalTime openTime;
    private String description;
    private boolean isDeleted;
    private String name;
    private String phone;
    private BigDecimal rating;
    private int userID;
//    private String streetNumber;
//    private String streetName;
//    private String district;
//    private String city;
//    private String province;
//    private String country;
private HotelAddressDTO hotelAddressDTO;

    private List<HotelImageDTO> listHotelImageDTO;
    private List<HotelReviewsDTO>  listHotelReviewsDTO;
    private User userEntity;




}
