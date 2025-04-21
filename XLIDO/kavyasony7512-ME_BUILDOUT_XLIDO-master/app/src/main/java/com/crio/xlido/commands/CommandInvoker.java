package com.crio.xlido.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String, ICommand> commands = new HashMap<>();
 
    // Register the command into the HashMap
    public void register(String commandName, ICommand command){
        commands.putIfAbsent(commandName,command);
    }
 
    // Get the registered Command
    public ICommand get(String commandName){
        return commands.get(commandName);
    }

    private List<String> parse(String input){
        return Arrays.asList(input.split(","));
    }
 
    // Invoke the registered Command
    public void invoke(String input){
        List<String> tokens = parse(input);
        ICommand command = get(tokens.get(0));
        if(command == null){
            throw new RuntimeException("INVALID_COMMAND");
        }
         command.invoke(tokens);
         
    }

}




// package com.crio.xlido.commands;

// import com.crio.xlido.services.QuestionService;
// import com.crio.xlido.services.UserService;
// import com.crio.xlido.services.EventService;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class CommandInvoker {
//     private final Map<String, ICommand> commands = new HashMap<>();

//     public CommandInvoker(UserService userService, EventService eventService,
//             QuestionService questionService) {

//         commands.put("CREATE_USER", (ICommand) new CreateUserCommand(userService));
//         commands.put("CREATE_EVENT", new CreateEventCommand(eventService));
//         commands.put("DELETE_EVENT", new DeleteEventCommand(eventService));
//         commands.put("ADD_QUESTION", new AddQuestionCommand(questionService));
//         commands.put("DELETE_QUESTION", new DeleteQuestionCommand(questionService));
//         commands.put("LIST_QUESTION", new ListQuestionsCommand(questionService));

//     }

//     public void execute(String commandLine) {
//         String[] tokens = commandLine.split(",");
//         String commandType = tokens[0].trim();
//         ICommand command = commands.get(commandType);

//         if (command != null) {
//             command.invoke(List.of(tokens));
//         } else {
//             System.out.println("Error: Unknown command.");
//         }
//     }
// }

