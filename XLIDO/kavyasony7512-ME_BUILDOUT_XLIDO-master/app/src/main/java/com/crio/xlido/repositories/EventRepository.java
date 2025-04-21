package com.crio.xlido.repositories;

// import com.crio.xlido.entities.Event;
// import java.util.*;
// import java.util.concurrent.atomic.AtomicLong;

// public class EventRepository implements IEventRepository {
//     private final Map<Long, Event> Storage = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(0);  // For thread-safe ID generation

//     @Override
//     public Event save(Event entity) {
//             Event contest = new Event(idCounter.incrementAndGet(),entity);
//             Storage.putIfAbsent(contest.getId(),contest);
//             return contest;
//     }

//     @Override
//     public List<Event> findAll() {
//         return new ArrayList<>(Storage.values());
//     }

//     @Override
//     public Optional<Event> findById(Long id) {
//         return Optional.ofNullable(Storage.get(id));
//     }
    
//     @Override
//     public void deleteById(Long id) {
//         Storage.remove(id);
//     }

// }

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Event;

public class EventRepository implements IEventRepository {

    private final Map<Long, Event> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Event save(Event entity) {
        // Create a new Event with a unique ID
        Event event = new Event(idCounter.incrementAndGet(), entity);
        storage.put(event.getId(), event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Event> findByEventId(Long eventId) {
       // Find all the Contestants for a given event Id.
       return storage.values().stream().filter(c -> c.getId() == eventId).collect(Collectors.toList());

    }

    // @Override
    // public void deleteById(Long eventId, Long userId) {
    //     // Remove the event if it exists
    //     Optional<Event> eventOpt = findById(eventId);
    //     if (eventOpt.isPresent() && eventOpt.get().getOrganizerId().equals(userId)) {
    //         storage.remove(eventId);
    //     } else {
    //         throw new RuntimeException("Event with id " + eventId + " and organizer id " + userId + " not found or unauthorized.");
    //     }
    // }

@Override
public void deleteById(Long eventId, Long userId) {
    Event event = storage.get(eventId);

    // Ensure that the event exists and the user is the organizer
    if (event != null && event.getId().equals(userId)) {  // Check organizer ID
        storage.remove(eventId);  // Directly remove the event using event ID
    }
}

        
    }