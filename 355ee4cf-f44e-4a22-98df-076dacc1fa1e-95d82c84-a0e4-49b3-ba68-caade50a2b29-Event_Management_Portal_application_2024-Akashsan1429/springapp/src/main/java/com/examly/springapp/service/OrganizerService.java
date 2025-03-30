package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Organizer;
import com.examly.springapp.repository.OrganizerRepository;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository repository;

    public List<Organizer> getAllOrganizers() {
        return repository.findAll();
    }

    public String addOrganizer(Organizer organizer) {
        
        repository.save(organizer);
        return "Organizer has Created";
    }

    public Organizer getOrganizerById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Organizer updateOrganizer(int id, Organizer updatedOrganizer) {
        if (repository.existsById(id)) {
            updatedOrganizer.setId(id);
            return repository.save(updatedOrganizer);
        }
        return null;
    }

    public boolean deleteOrganizer(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Organizer> getOrganizersByName(String name) {
        return repository.findByName(name);
    }

    public Page<Organizer> getAllOrganizersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Optional<Organizer> getById(int id) {
        
        return repository.findById(id);
    }
} 
