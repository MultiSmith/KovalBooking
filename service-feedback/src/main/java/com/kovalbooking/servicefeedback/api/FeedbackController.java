package com.kovalbooking.servicefeedback.api;

import com.kovalbooking.servicefeedback.api.dto.FeedbackDTO;
import com.kovalbooking.servicefeedback.api.dto.UserDTO;
import com.kovalbooking.servicefeedback.repo.model.Feedback;
import com.kovalbooking.servicefeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedbacks")
public final class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> index() {

        final List<Feedback> feedbacks = feedbackService.fetchAll();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{feedback_id}")
    public ResponseEntity<Feedback> showById(@PathVariable long feedback_id) {
        try {
            final Feedback feedback = feedbackService.fetchById(feedback_id);

            return ResponseEntity.ok(feedback);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{feedback_id}/user")
    public ResponseEntity<UserDTO> showUserByFeedbackId(@PathVariable long feedback_id) {
        try {
            final UserDTO userDTO = feedbackService.getUserByFeedbackId(feedback_id);

            return ResponseEntity.ok(userDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FeedbackDTO feedback) {
        final long hotel_id = feedback.getHotel_id();
        final long user_id = feedback.getUser_id();
        final Integer rating = feedback.getRating();
        final String comment = feedback.getComment();

        final long feedback_id = feedbackService.create(hotel_id, user_id, rating, comment);
        final String location = String.format("/feedbacks/%d", feedback_id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{feedback_id}")
    public ResponseEntity<Void> update(@PathVariable long feedback_id, @RequestBody FeedbackDTO feedback) {
        final Integer rating = feedback.getRating();
        final String comment = feedback.getComment();

        try {
            feedbackService.update(feedback_id, rating, comment);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{feedback_id}")
    public ResponseEntity<Void> delete(@PathVariable long feedback_id) {
        feedbackService.delete(feedback_id);
        return ResponseEntity.noContent().build();
    }
}
