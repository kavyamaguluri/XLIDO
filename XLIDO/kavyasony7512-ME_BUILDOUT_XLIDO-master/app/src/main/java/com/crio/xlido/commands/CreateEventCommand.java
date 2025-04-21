package com.crio.xlido.commands;

import com.crio.xlido.entities.Event;
import com.crio.xlido.services.EventService;
import java.util.List;

public class CreateEventCommand implements ICommand {
    private final EventService eventService;

    public CreateEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
            System.out.println("Error: Invalid number of arguments for CREATE_EVENT command.");
            return;
        }

        String title = tokens.get(1).trim();
        Long organizerId;
        try {
            organizerId = Long.parseLong(tokens.get(2).trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid organizer ID format.");
            return;
        }

        Event result = null; // Initialize result
        try {
            result = eventService.createEvent(title, organizerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return; // Early exit if an error occurs
        }
        
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Error: Event creation failed.");
        }
    }
}
