package com.Thienbao.booking.entity;






import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "hotel_reviews")
@Data
public class HotelReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;




    @OneToMany(mappedBy = "hotelReviewId")
    private List<ReviewReplies> reviewReplies;









}
