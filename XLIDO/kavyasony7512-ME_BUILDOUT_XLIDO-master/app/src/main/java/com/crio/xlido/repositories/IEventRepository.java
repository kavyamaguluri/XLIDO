package com.crio.xlido.repositories;

import com.crio.xlido.entities.Event;
import java.util.List;
import java.util.Optional;

public interface IEventRepository {
    // Event save(Event event);
    // Optional<Event> findById(Long id);
    // List<Event> findAll();
    // void deleteById(Long id);

    Event save(Event entity);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    List<Event> findByEventId(Long eventId);
    void deleteById(Long eventId, Long userId);
}
