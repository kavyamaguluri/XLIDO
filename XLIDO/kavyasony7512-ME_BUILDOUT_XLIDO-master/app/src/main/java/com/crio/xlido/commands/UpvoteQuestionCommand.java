package com.crio.xlido.commands;

import com.crio.xlido.services.QuestionService;
import java.util.List;

public class UpvoteQuestionCommand implements ICommand {
    private final QuestionService questionService;

    public UpvoteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
            System.out.println("Error: Invalid number of arguments for UPVOTE_QUESTION command.");
            return;
        }

        Long questionId;
        Long userId;
        try {
            questionId = Long.parseLong(tokens.get(1).trim());
            userId = Long.parseLong(tokens.get(2).trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for question ID or user ID.");
            return;
        }

        try {
            String result = questionService.upvoteQuestion(questionId, userId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
