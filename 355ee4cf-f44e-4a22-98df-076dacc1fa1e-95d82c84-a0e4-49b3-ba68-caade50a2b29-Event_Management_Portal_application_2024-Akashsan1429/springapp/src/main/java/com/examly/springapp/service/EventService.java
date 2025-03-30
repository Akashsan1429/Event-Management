package com.examly.springapp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Event;
import com.examly.springapp.entity.Organizer;
import com.examly.springapp.repository.EventRepository;
import com.examly.springapp.repository.OrganizerRepository;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAllEvents() {
        return repository.findAll();
    }
    @Autowired
    private OrganizerRepository organizerRepository;

    public String addEvent(Event event) {
        repository.save(event);
        return "Event Created";
    }


    public Event getEventById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Event updateEvent(int id, Event updatedEvent) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        
        Event existingEvent = repository.findById(id).orElseThrow(() -> new RuntimeException("Event not found."));
    
        if (updatedEvent.getOrganizer() != null && updatedEvent.getOrganizer().getId() != 0) {
            Organizer organizer = organizerRepository.findById(updatedEvent.getOrganizer().getId())
                    .orElseThrow(() -> new RuntimeException("Organizer not found."));
            existingEvent.setOrganizer(organizer);
        }

        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setTime(updatedEvent.getTime());
        existingEvent.setAvailableSeats(updatedEvent.getAvailableSeats());
    
        return repository.save(existingEvent);
    }

    public boolean deleteEvent(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Event> getEventsByLocation(String location) {
        return repository.findByLocation(location);
    }

    public List<Event> getEventsByOrganizerId(int organizerId) {
        return repository.findEventsByOrganizerId(organizerId);
    }

    public Page<Event> getAllEventsPaginated(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }
    public String deleteEventByVenueId(int venueId) {
        if (repository.findEventsByVenueId(venueId).isEmpty()) {
            return "No events found for Venue ID: " + venueId;
        }
        repository.deleteByVenueId(venueId);
        return "Events deleted for Venue ID: " + venueId;
    }
    public Event updateEventByVenueId(int venueId, Event updatedEvent) {
        List<Event> events = repository.findEventsByVenueId(venueId);
        if (events.isEmpty()) {
            throw new RuntimeException("No events found for Venue ID: " + venueId);
        }

        for (Event event : events) {
            event.setEventName(updatedEvent.getEventName());
            event.setLocation(updatedEvent.getLocation());
            event.setDate(updatedEvent.getDate());
            event.setTime(updatedEvent.getTime());
            event.setAvailableSeats(updatedEvent.getAvailableSeats());
            repository.save(event);
        }
        return events.get(0);
    }
    public List<Event> getEventsByVenueId(int venueId) {
        return repository.findEventsByVenueId(venueId);
    }
    public String deleteEventByUserId(int userId) {
        if (repository.findEventsByOrganizerId(userId).isEmpty()) {
            return "No events found for User ID: " + userId;
        }
        repository.deleteByOrganizerId(userId);
        return "Events deleted for User ID: " + userId;
    }
    public Event updateEventByUserId(int userId, Event updatedEvent) {
        List<Event> events = repository.findEventsByOrganizerId(userId);
        if (events.isEmpty()) {
            throw new RuntimeException("No events found for User ID: " + userId);
        }

        for (Event event : events) {
            event.setEventName(updatedEvent.getEventName());
            event.setLocation(updatedEvent.getLocation());
            event.setDate(updatedEvent.getDate());
            event.setTime(updatedEvent.getTime());
            event.setAvailableSeats(updatedEvent.getAvailableSeats());
            repository.save(event);
        }
        return events.get(0);
    }
    public List<Event> getEventsByUsername(int id) {
        return repository.findEventsByOrganizerId(id);
    }




} 
