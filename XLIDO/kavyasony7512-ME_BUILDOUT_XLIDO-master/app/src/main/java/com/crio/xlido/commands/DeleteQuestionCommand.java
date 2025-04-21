package com.crio.xlido.commands;

import com.crio.xlido.services.QuestionService;
import java.util.List;

public class DeleteQuestionCommand implements ICommand {
    private final QuestionService questionService;

    public DeleteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long questionId = Long.parseLong(tokens.get(1).trim());
        Long userId = Long.parseLong(tokens.get(2).trim());

        try {
            questionService.deleteQuestion(questionId, userId);
            System.out.println("QUESTION_DELETED " + questionId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
