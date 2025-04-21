package com.crio.xlido.services;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.List;
// import java.util.Optional;

// import com.crio.xlido.entities.Question;
// import com.crio.xlido.entities.Event;
// import com.crio.xlido.entities.User;
// import com.crio.xlido.repositories.IQuestionRepository;
// import com.crio.xlido.repositories.IUserRepository;
// import com.crio.xlido.repositories.IEventRepository;

// public class QuestionService {
//     private final IQuestionRepository questionRepository;
//     private final IUserRepository userRepository;
//     private final IEventRepository eventRepository;

//     public QuestionService(IQuestionRepository questionRepository, IUserRepository userRepository,
//             IEventRepository eventRepository) {
//         this.questionRepository = questionRepository;
//         this.userRepository = userRepository;
//         this.eventRepository = eventRepository;
//     }

//     public Question addQuestion(String content, Long userId, Long eventId) throws Exception {
//         User user = userRepository.findById(userId).orElseThrow(
//                 () -> new Exception("ERROR: User with an id " + userId + " does not exist"));

//         Event event = eventRepository.findById(eventId).orElseThrow(
//                 () -> new Exception("ERROR: Event with an id " + eventId + " does not exist"));

//         return questionRepository.save(new Question(content, event, user));
//     }

//     public void deleteQuestion(Long questionId, Long userId) throws Exception {
//         Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception(
//                 "ERROR: Question with an id " + questionId + " does not exist"));

//         if (!userRepository.findById(userId).isPresent()) {
//             throw new RuntimeException("ERROR: "+"User with an id " + userId + " does not exist");
//         }

//         if (!question.getUser().getId().equals(userId)) {
//             throw new Exception("ERROR: User with an id " + userId
//                     + " is not an author of question with an id " + questionId);
//         }
//         questionRepository.deleteById(questionId);
//     }

//     public String upvoteQuestion(Long questionId, Long userId) throws Exception {
//         Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception(
//                 "ERROR: Question with an id " + questionId + " does not exist"));

//         User user = userRepository.findById(userId).orElseThrow(
//                 () -> new Exception("ERROR: User with an id " + userId + " does not exist"));

//         Question updatedQuestion = questionRepository.upvoteQuestionRepo(questionId, userId);

//         // Return the questionId as a String
//         return "Question ID: " + updatedQuestion.getId();
//     }

//     public List<Question> listQuestion(Long eventId, String sortingLogic) throws Exception {
//         Event event = eventRepository.findById(eventId)
//             .orElseThrow(() -> new Exception("ERROR: Event with an id " + eventId + " does not exist"));

//         List<Question> allQuestions = questionRepository.findAll();
//         List<Question> questionsInEvent = new ArrayList<>();
//         for (Question question : allQuestions) {
//             if (question.getEvent().getId().equals(eventId)) {
//                 questionsInEvent.add(question);
//             }
//         }
//         return questionsInEvent;
//     }

// }

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IQuestionRepository;
import com.crio.xlido.repositories.IUserRepository;

public class QuestionService {

    private final IQuestionRepository questionRepository;
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;

    public QuestionService(IQuestionRepository questionRepository, IEventRepository eventRepository, IUserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Question addQuestion(String content, Long userId, Long eventId) {
        // Validate the user exists
        if (!userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }

        // Validate the event exists
        if (!eventRepository.findById(eventId).isPresent()) {
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }

        // Create and save a new question
        Question question = new Question(null, content, userId, eventId);
        return questionRepository.save(question);
    }

    public boolean deleteQuestion(Long questionId, Long userId) {
        // Check if the question exists
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (!questionOpt.isPresent()) {
            throw new RuntimeException("Question with an id " + questionId + " does not exist");
        }

        Question question = questionOpt.get();

        // Check if the user exists
        if (!userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }

        // Check if the user is the author of the question
        if (!question.getUserId().equals(userId)) {
            throw new RuntimeException("User with an id " + userId + " is not an author of question with an id " + questionId);
        }

        // Delete the question if all validations pass
        questionRepository.deleteById(questionId);
        return true;
    }

    public String upvoteQuestion(Long questionId, Long userId) {
        // Check if the question exists
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (!questionOpt.isPresent()) {
            throw new RuntimeException("Question with an id " + questionId + " does not exist");
        }

       
        // Check if the user exists
        if (!userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }

        Question question = questionOpt.get();

        // Check if the user has already upvoted the question
        if (question.hasUpvoted(userId)) {
            throw new RuntimeException("User with an id " + userId + " has already upvoted a question with an id " + questionId);
        }

        // Add the upvote
        question.addUpvote(userId);
        return null;

        // Save the updated question
        // questionRepository.save(question);
    }

    public String replyToQuestion(String content, Long questionId, Long userId) {
        // Check if the question exists
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (!questionOpt.isPresent()) {
            return "ERROR: Question with an id " + questionId + " does not exist";
        }

        // Check if the user exists
        if (!userRepository.findById(userId).isPresent()) {
            return "ERROR: User with an id " + userId + " does not exist";
        }

        // Add the reply to the question
        Question question = questionOpt.get();
        question.addReply(content, userId);
       // questionRepository.save(question);

        return "REPLY_ADDED";
    }

       // Method to list questions based on event ID and sorting criteria
       public List<Question> listQuestions(Long eventId, String sortBy) {
        if (!eventRepository.findById(eventId).isPresent()) {
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }

        List<Question> questions = questionRepository.findByEventId(eventId);

        if ("POPULAR".equalsIgnoreCase(sortBy)) {
            questions.sort(Comparator.comparingInt(Question::getVoteCount).reversed());
        } else if ("RECENT".equalsIgnoreCase(sortBy)) {
            questions.sort(Comparator.comparing(Question::getCreatedAt).reversed());
        }

        return questions;
    }
   
}
