package com.kovalbooking.servicefeedback.service;

import com.kovalbooking.servicefeedback.api.dto.UserDTO;
import com.kovalbooking.servicefeedback.repo.FeedbackRepo;
import com.kovalbooking.servicefeedback.repo.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class FeedbackService {
    private final FeedbackRepo feedbackRepo;
    private final String userURL = "http://service-identity:8082/users/";

    public List<Feedback> fetchAll() {
        return feedbackRepo.findAll();
    }

    public Feedback fetchById(long feedback_id) throws IllegalArgumentException {
        final Optional<Feedback> optionalFeedback = feedbackRepo.findById(feedback_id);
        if (optionalFeedback.isEmpty()) throw new IllegalArgumentException("No feedbacks found!");
        else return optionalFeedback.get();
    }

    public long create(long hotel_id, long user_id, Integer rating, String comment) {
        final Feedback feedback = new Feedback(hotel_id, user_id, rating, comment);
        final Feedback saved = feedbackRepo.save(feedback);
        return saved.getFeedback_id();
    }

    public void update(long feedback_id, Integer rating, String comment) throws IllegalArgumentException {
        final Optional<Feedback> optionalFeedback = feedbackRepo.findById(feedback_id);
        if (optionalFeedback.isEmpty()) throw new IllegalArgumentException("No feedbacks found!");

        final Feedback feedback = optionalFeedback.get();
        if(rating != null && !rating.toString().isBlank()) feedback.setRating(rating);
        if(comment != null && !comment.isEmpty()) feedback.setComment(comment);

        feedbackRepo.save(feedback);
    }

    public void delete(long feedback_id) throws IllegalArgumentException {
        feedbackRepo.deleteById(feedback_id);
    }

    public UserDTO getUserByFeedbackId(long feedback_id) {
        final Optional<Feedback> optionalFeedback = feedbackRepo.findById(feedback_id);
        if (optionalFeedback.isEmpty()) throw new IllegalArgumentException("No feedbacks found!");

        final Feedback feedback = optionalFeedback.get();
        final long feedback_user_id = feedback.getUser_id();
        final String feedback_user_idURL = userURL + feedback_user_id;

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserDTO> response = restTemplate.exchange(feedback_user_idURL, HttpMethod.GET, null, new ParameterizedTypeReference<UserDTO>() {});
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) throw new IllegalArgumentException("No user found!");

        return response.getBody();
    }
}
