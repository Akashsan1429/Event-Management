package com.examly.springapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Venue;
import com.examly.springapp.service.VenueService;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService service;

    @GetMapping
    public List<Venue> getAllVenues() {
        return service.getAllVenues();
    }

    @PostMapping
    public Venue addVenue(@Valid @RequestBody Venue venue) {
        return service.addVenue(venue);
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable int id) {
        return service.getVenueById(id);
    }

    @PutMapping("/{id}")
    public Venue updateVenue(@PathVariable int id, @Valid @RequestBody Venue updatedVenue) {
        return service.updateVenue(id, updatedVenue);
    }

    @DeleteMapping("/{id}")
    public String deleteVenue(@PathVariable int id) {
        return service.deleteVenue(id) ? "Venue Deleted" : "Venue Not Found";
    }

    @GetMapping("/location")
    public List<Venue> getVenuesByLocation(@RequestParam String location) {
        return service.getVenuesByLocation(location);
    }

    @GetMapping("/page")
    public Page<Venue> getAllVenuesPaginated(@RequestParam int page, @RequestParam int size) {
        return service.getAllVenuesPaginated(page, size);
    }
}
