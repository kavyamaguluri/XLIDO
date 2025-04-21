package com.crio.xlido.commands;

// import com.crio.xlido.entities.Reply;
// import com.crio.xlido.services.ReplyService;
// import java.util.List;

// public class ReplyQuestionCommand implements ICommand {
//     private final ReplyService replyService;

//     public ReplyQuestionCommand(ReplyService replyService) {
//         this.replyService = replyService;
//     }

//     @Override
//     public void invoke(List<String> tokens) {
//        // StringBuilder output = new StringBuilder();
//        String content=tokens.get(1);
//         Long questionId= Long.parseLong(tokens.get(2));
//         Long userid =Long.parseLong(tokens.get(3));
//         try {
//             Reply reply=new Reply(questionId,userid,content);
//             replyService.createReply(reply);
//            System.out.println("REPLY_ADDED");
//         } catch (Exception e) {
//             System.out.println("ERROR: "+e.getMessage());
//         }
       
//     }
// }

    // @Override
    // public void invoke(List<String> tokens) {
    //     if (tokens.size() < 4) {
    //         System.out.println("Error: Invalid number of arguments for REPLY_QUESTION command.");
    //         return;
    //     }

    //     Reply Reply = tokens.get(1).trim();
    //     Long questionId;
    //     Long userId;

    //     try {
    //         questionId = Long.parseLong(tokens.get(2).trim());
    //         userId = Long.parseLong(tokens.get(3).trim());
    //     } catch (NumberFormatException e) {
    //         System.out.println("Error: Invalid number format for question ID or user ID.");
    //         return;
    //     }

    //     try {
    //         replyService.createReply(reply);
    //         System.out.println("Reply added successfully.");
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }
    // }
// }


