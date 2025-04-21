package com.crio.xlido.commands;

import com.crio.xlido.services.QuestionService;
import com.crio.xlido.entities.Question;
import java.util.List;

public class ListQuestionsCommand implements ICommand {
    private final QuestionService questionService;

    public ListQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
            System.out.println("Error: Invalid number of arguments for LIST_QUESTIONS command.");
            return;
        }

        Long eventId;
        String sortBy;
        try {
            eventId = Long.parseLong(tokens.get(1).trim());
            sortBy = tokens.get(2).trim().toUpperCase();
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
            return;
        }

        try {
            List<Question> questions = questionService.listQuestions(eventId, sortBy);

            if (questions.isEmpty()) {
                System.out.println("No questions found.");
            } else {
                for (Question question : questions) {
                    System.out.println(question.toString());
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: An unexpected error occurred.");
        }
    }
}
