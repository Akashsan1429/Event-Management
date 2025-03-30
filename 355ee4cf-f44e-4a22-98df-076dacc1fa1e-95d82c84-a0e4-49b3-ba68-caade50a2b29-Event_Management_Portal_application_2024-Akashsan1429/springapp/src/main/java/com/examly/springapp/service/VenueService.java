package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Venue;
import com.examly.springapp.repository.VenueRepository;

@Service
public class VenueService {

    @Autowired
    private VenueRepository repo;

    public Venue addVenue(Venue venue) {
        if (repo.existsByName(venue.getName())) {
            throw new RuntimeException("Venue name already exists");
        }
        return repo.save(venue);
    }

    public List<Venue> getAllVenues() {
        return repo.findAll();
    }

    public Venue getVenueById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public Venue updateVenue(int id, Venue updatedVenue) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Venue not found");
        }
        updatedVenue.setId(id);
        return repo.save(updatedVenue);
    }

    public boolean deleteVenue(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Venue not found");
        }
        repo.deleteById(id);
        return true;
    }

    public List<Venue> getVenuesByLocation(String location) {
        return repo.findByLocation(location);
    }

    public Page<Venue> getAllVenuesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
}
