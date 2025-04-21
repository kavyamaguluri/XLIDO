package com.crio.xlido.repositories;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.Set;
// import java.util.concurrent.atomic.AtomicLong;
// import java.util.stream.Collectors;
// import com.crio.xlido.entities.Question;

// public class QuestionRepository implements IQuestionRepository {

//     private final Map<Long, Question> Storage = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(0);

//     public Question save(Question entity) {
//         Question question = new Question(idCounter.incrementAndGet(), entity);
//         Storage.putIfAbsent(question.getId(), question);
//         return question;
//     }

//     @Override
//     public List<Question> findAll() {
//         return new ArrayList<>(Storage.values());
//     }

//     @Override
//     public Optional<Question> findById(Long id) {
//         return Optional.ofNullable(Storage.get(id));
//     }

//     @Override
//     public List<Question> findByEventId(Long eventId) {
//         // Return all questions associated with the given event ID
//         return Storage.values().stream()
//                 .filter(question -> question.getEventId().equals(eventId))
//                 .collect(Collectors.toList());
//     }

//     @Override
//     public void deleteById(Long id) {
//         Storage.remove(id);
//     }

//     @Override
//     public Question upvoteQuestionRepo(Long questionId, Long userId) {
//         Question question = Storage.get(questionId);
//         if (question == null) {
//             throw new RuntimeException("Question with id " + questionId + " does not exist.");
//         }

//         List<Long> upvotes = question.getUpvotedBy();
//         if (upvotes == null) {
//             upvotes = new ArrayList<>();
//             question.setUpvotedBy(upvotes);
//         }

//         if (upvotes.contains(userId)) {
//             throw new RuntimeException("User with id " + userId + " has already upvoted question with id " + questionId);
//         }

//         upvotes.add(userId);
//         question.setUpVotes(upvotes.size()); // Update the vote count

//         return question;
//     }
// }

import com.crio.xlido.entities.Question;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class QuestionRepository implements IQuestionRepository {

    private final Map<Long, Question> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Question save(Question question) {
        // Assign a new ID to the question and save it in the storage
        Question newQuestion = new Question(idCounter.incrementAndGet(), question.getContent(), question.getUserId(), question.getEventId());
        storage.put(newQuestion.getId(), newQuestion);
        return newQuestion;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Question> findByEventId(Long eventId) {
        // Return all questions associated with the given event ID
        return storage.values().stream()
                .filter(question -> question.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long questionId) {
        storage.remove(questionId);
    }

 // Optional: Additional method to update a question
 public Question update(Question question) {
    if (question.getId() != null && storage.containsKey(question.getId())) {
        storage.put(question.getId(), question);
        return question;
    }
    throw new RuntimeException("Question with id " + question.getId() + " does not exist.");
}

// Additional helper method to list all questions (if needed for other operations)
@Override
public Map<Long, Question> findAll() {              
    return new HashMap<>(storage);
}

}
