package com.crio.xlido.services;

// import com.crio.xlido.entities.Event;
// import com.crio.xlido.entities.User;
// import com.crio.xlido.repositories.IEventRepository;
// import com.crio.xlido.repositories.IUserRepository;

// import java.util.Optional;

// public class EventService {
//     private final IEventRepository eventRepository;
//     private final IUserRepository userRepository;

//     public EventService(IEventRepository eventRepository, IUserRepository userRepository) {
//         this.eventRepository = eventRepository;
//         this.userRepository = userRepository;
//     }

//     public Event createEvent(String title, Long organizerId) throws Exception {
//         Optional<User> user = userRepository.findById(organizerId);
//         if (user.isEmpty()) {
//             throw new Exception("ERROR: User with an id " + organizerId + " does not exist");
//         }
//         Event event = new Event(title, organizerId);
//         return eventRepository.save(event);
//     }

//     public Event getEvent(Long eventId) throws Exception {
//         Optional<Event> event = eventRepository.findById(eventId);
//         if (event.isEmpty()) {
//             throw new Exception("ERROR: Event with an id " + eventId + " does not exist");
//         }
//         return event.get();
//     }

//     public void deleteEvent(Long eventId, Long userId) throws Exception {
//         Optional<User> user = userRepository.findById(userId);
//         if (user.isEmpty()) {
//             throw new Exception("ERROR: User with an id " + userId + " does not exist");
//         }

//         Event event = getEvent(eventId);
//         if (!event.getUserId().equals(userId)) {
//             throw new Exception("ERROR: User with an id " + userId + " is not a organizer of Event with an id " + eventId);
//         }

//         eventRepository.deleteById(eventId);
//     }
// }

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;
import com.crio.xlido.entities.Event;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IUserRepository;

public class EventService {

    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;  // Add a reference to UserRepository

    public EventService(IEventRepository eventRepository, IUserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    public Event createEvent(String title, Long organizerId) {

        // Validate that the title is not empty
        if (title == null || title.trim().isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }

        // Check if the organizer exists
        if (!userRepository.findById(organizerId.longValue()).isPresent()) {
            throw new RuntimeException("User with an id " + organizerId + " does not exist");
        }

        // Create a new Event
        Event event = new Event(title, organizerId);
        return eventRepository.save(event);
    }

    // public boolean deleteEvent(Long eventId, Long userId){

    //      // find the events by eventId 
    //      Optional<Event> eventOpt = eventRepository.findById(eventId);


    //      //check if the event exists
    //      if (!eventOpt.isPresent()) {
    //         throw new RuntimeException("Event with an id " + eventId + " does not exist");
    //     }
       
    //     Event event = eventOpt.get();

    //      // Check if the user exists
    //      if (!userRepository.findById(userId).isPresent()) {
    //         throw new RuntimeException("User with an id " + userId + " does not exist");
    //     }
    
    //     // Check if the user is the organizer of the event
    // if (!event.getId().equals(userId)) {
    //     throw new RuntimeException("User with an id " + userId + " is not a organizer of Event with an id " + eventId);
    // }

        
    //      // Delete the event if all validations pass
    // eventRepository.deleteById(eventId, userId); // This method should remove the event directly
    
    // return true;

    // }

    public boolean deleteEvent(Long eventId, Long userId) {

        // Find the event by eventId
        Optional<Event> eventOpt = eventRepository.findById(eventId);
    
        // Check if the event exists
        if (!eventOpt.isPresent()) {
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }
    
        Event event = eventOpt.get();
    
        // Check if the user exists
        if (!userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }
    
        // Check if the user is the organizer of the event
        if (!event.getOrganizerId().equals(userId)) {  // Corrected to use organizer ID
            throw new RuntimeException("User with an id " + userId + " is not a organizer of Event with an id " + eventId);
        }
    
        // Delete the event if all validations pass
        eventRepository.deleteById(eventId, userId); // Remove directly by eventId
    
        return true;
    }
    
}