package com.Thienbao.booking.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity (name = "hotels")
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "avatar", length = 600)
    private String avatar;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name="phone", length = 15)
    private String phone;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "checkin_time")
    private LocalTime checkInTime;

    @Column(name = "checkout_time")
    private LocalTime checkOutTime;

    @Column(name = "rating", columnDefinition = "DECIMAL(2,1)")
    private BigDecimal rating;






    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne(mappedBy = "hotelIdFk", cascade = CascadeType.ALL)
    private HotelAddress HotelAddress;

    @OneToMany(mappedBy = "hotelId")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotelId")
    private List<HotelReviews> hotelReviews;

    @OneToMany(mappedBy = "hotelId")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "hotelId")
    private List<Room> roomList;

    @OneToMany(mappedBy = "hotelIdFk")
    private List<HotelAmenities> hotelAmenitiesList;
//
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private User user;

//    @Column(name = "checkin_time")
//    private LocalTime checkinTime;
//
//    @Column(name = "checkout_time")
//    private LocalTime checkoutTime;
}
