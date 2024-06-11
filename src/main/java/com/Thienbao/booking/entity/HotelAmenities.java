package com.Thienbao.booking.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAmenities {

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    @Id
    @Column(name = "amenities_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int amenityId;

    @ManyToOne
    @JoinColumn(name="amenities_id")
    private Amenities amenityIdFk;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelIdFk;










//    @JoinColumn(name = "hotel_id",referencedColumnName = "id", insertable = false, updatable = false)
//    private Hotel hotel;
//
//
//    @ManyToOne
//    @JoinColumn(name = "amenity_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Amenities amenity;

}
