package com.Thienbao.booking.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class HotelReviewsDTO {

    private int id;
    private int hotelId;
    private int userId;
    private String comment;
    private LocalDateTime reviewDate;
}
