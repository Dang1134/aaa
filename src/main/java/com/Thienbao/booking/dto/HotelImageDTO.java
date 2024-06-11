package com.Thienbao.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class HotelImageDTO {
    private int id;
    private int hotelID;
    private String imageTitle;
    private String imageDescription;
    private String imagePath;
    private LocalDateTime uploadDate;
}
