package com.crio.xlido.commands;

import com.crio.xlido.services.QuestionService;
import com.crio.xlido.entities.Question;
import java.util.List;

public class AddQuestionCommand implements ICommand {
    private final QuestionService questionService;

    public AddQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String content=tokens.get(1);
        Long userid= Long.parseLong(tokens.get(2));
        Long eventid =Long.parseLong(tokens.get(3));
        try {
          Question q=  questionService.addQuestion(content,userid,eventid);
           System.out.println(q.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}


