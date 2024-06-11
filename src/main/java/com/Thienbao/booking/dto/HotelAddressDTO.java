package com.Thienbao.booking.dto;

import lombok.Data;

@Data
public class HotelAddressDTO {
    //    private int id;
    private String streetName;
    private String streetNumber;
    private String district;
    private String city;
    private String province;
    private String country;
}
