package com.Thienbao.booking.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room_amenities")
@Data
public class RoomAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenities_id")
    private int amenityId;

//    @ManyToOne
//    @JoinColumn(name="room_id")
//    private RoomsEntity rooms;

    @ManyToOne
    @MapsId
    @JoinColumn(name="amenities_id")
    private Amenities amenityIdFk;


    @ManyToOne
    @MapsId
    @JoinColumn(name="room_id")
    private Room roomIdFk;






//    @ManyToOne
//    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Room room;
//
//
//    @ManyToOne
//    @JoinColumn(name = "amenity_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Amenities amenity;

}
