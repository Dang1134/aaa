package com.Thienbao.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "room_number")
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('AVAILABLE','BOOKED','OCCUPIED','MAINTENANCE','CLEANING') DEFAULT 'AVAILABLE'")
    private ROOM_STATUS status;

    @Lob
    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotelId;

    @ManyToOne
    @JoinColumn(name = "roomtype_id",referencedColumnName = "id")
    private RoomType roomTypeId;


    @OneToMany(mappedBy = "roomId")
    private List<RoomImage> roomImageList;

    @OneToMany(mappedBy = "roomId")
    private List<BookingRoom> bookingRoomList;


    @OneToMany(mappedBy = "roomIdFk")
    private List<RoomAmenities> roomAmenitiesList;

    //    @ManyToOne
//    @JoinColumn(name = "roomtype_id",referencedColumnName = "id")
//    private RoomType roomType;


//    @Column(name = "room_number")
//    private int roomNumber;

    //    @ManyToOne
//    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
//    private Hotel hotel;

}
