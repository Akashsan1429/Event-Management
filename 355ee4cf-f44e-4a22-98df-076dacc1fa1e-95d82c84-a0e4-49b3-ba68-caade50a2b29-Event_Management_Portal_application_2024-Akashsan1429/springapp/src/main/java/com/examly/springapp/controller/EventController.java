package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Event;
import com.examly.springapp.service.EventService;
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @PostMapping
    public String addEvent(@RequestBody Event event) {
        return service.addEvent(event);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return service.getEventById(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        return service.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable int id) {
        return service.deleteEvent(id) ? "Event Deleted" : "Event Not Found";
    }

    @GetMapping("/location/{location}")
    public List<Event> getEventsByLocation(@PathVariable String location) {
        return service.getEventsByLocation(location);
    }

    @GetMapping("/organizer/{organizerId}")
    public List<Event> getEventsByOrganizerId(@PathVariable int organizerId) {
        return service.getEventsByOrganizerId(organizerId);
    }

    
    @GetMapping("/page")
public Page<Event> getAllEventsPaginated(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDirection) {

    Sort sort = sortDirection.equalsIgnoreCase("asc") ?
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

    return service.getAllEventsPaginated(page, size, sort);
}

// @GetMapping("/user/{id}")
// public List<Event> getEventsByUsername(@PathVariable int id) {
//     return service.getEventsByname(id);
// }

@PutMapping("/user/{userId}")
public Event updateEventByUserId(@PathVariable int userId, @RequestBody Event updatedEvent) {
    return service.updateEventByUserId(userId, updatedEvent);
}

// @DeleteMapping("/user/{userId}")
// public String deleteEventByUserId(@PathVariable int userId) {
//     return service.deleteEventByUserId(userId) ? "Event Deleted" : "Event Not Found";
// }

@GetMapping("/venue/{venueId}")
public List<Event> getEventsByVenueId(@PathVariable int venueId) {
    return service.getEventsByVenueId(venueId);
}

@PutMapping("/venue/{venueId}")
public Event updateEventByVenueId(@PathVariable int venueId, @RequestBody Event updatedEvent) {
    return service.updateEventByVenueId(venueId, updatedEvent);
}

// @DeleteMapping("/venue/{venueId}")
// public String deleteEventByVenueId(@PathVariable int venueId) {
//     return service.deleteEventByVenueId(venueId) ? "Event Deleted" : "Event Not Found";
// }
}


