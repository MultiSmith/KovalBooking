package com.kovalbooking.servicefeedback.repo;

import com.kovalbooking.servicefeedback.repo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

}
