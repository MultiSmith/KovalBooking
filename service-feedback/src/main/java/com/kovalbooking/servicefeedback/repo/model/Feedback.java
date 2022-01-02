package com.kovalbooking.servicefeedback.repo.model;

import org.hibernate.type.ShortType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "feedbacks")
public final class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedback_id;
    private long hotel_id;
    private long user_id;
    private Integer rating;
    private String comment;

    public Feedback(long hotel_id, long user_id,Integer rating, String comment) {
        this.hotel_id = hotel_id;
        this.user_id = user_id;
        this.rating = rating;
        this.comment = comment;
    }

    public Feedback() {
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
