package com.crio.xlido.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {
    // Question save(Question question);
    // Optional<Question> findById(Long id);
    // List<Question> findByEventId(Long eventId);
    // List<Question> findAll();
    // void deleteById(Long id);    
    // Question upvoteQuestionRepo(Long questionId, Long userId) ;

    Question save(Question question);
    Optional<Question> findById(Long id);
    List<Question> findByEventId(Long eventId);
    void deleteById(Long questionId);  // Method to delete question by ID
    Map<Long, Question> findAll();
}

