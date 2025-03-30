package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

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

import com.examly.springapp.entity.Organizer;
import com.examly.springapp.service.OrganizerService;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService service;

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return service.getAllOrganizers();
    }

    @PostMapping
    public String addOrganizer(@RequestBody Organizer organizer) {
        return service.addOrganizer(organizer);
    }

    @GetMapping("/{id}")
    public Organizer getOrganizerById(@PathVariable int id) {
        return service.getOrganizerById(id);
    }

    @PutMapping("/{id}")
    public Organizer updateOrganizer(@PathVariable int id, @RequestBody Organizer updatedOrganizer) {
        return service.updateOrganizer(id, updatedOrganizer);
    }

    @DeleteMapping("/{id}")
    public String deleteOrganizer(@PathVariable int id) {
        return service.deleteOrganizer(id) ? "Organizer Deleted" : "Organizer Not Found";
    }

    @GetMapping("/name/{name}")
    public List<Organizer> getOrganizersByName(@PathVariable String name) {
        return service.getOrganizersByName(name);
    }

    @GetMapping("/page")
    public Page<Organizer> getAllOrganizersPaginated(@RequestParam int page, @RequestParam int size) {
        return service.getAllOrganizersPaginated(page, size);
    }
    @GetMapping("path")
    public Optional<Organizer> getbyId(@RequestParam int id) {
        return service.getById(id);
    }
    
}