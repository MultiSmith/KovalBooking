package com.kovalbooking.servicehotel.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackDTO {
    private long feedback_id;
    private long user_id;
    private Integer rating;
    private String comment;
}