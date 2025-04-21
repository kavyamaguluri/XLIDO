package com.crio.xlido.commands;

import com.crio.xlido.services.EventService;

import java.util.List;

public class DeleteEventCommand implements ICommand {
    private final EventService eventService;

    public DeleteEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            Long eventId = Long.parseLong(tokens.get(1).trim()); // Convert to Long
            Long userId = Long.parseLong(tokens.get(2).trim()); // Convert to Long

            eventService.deleteEvent(eventId, userId);
            System.out.println("EVENT_DELETED " + eventId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
