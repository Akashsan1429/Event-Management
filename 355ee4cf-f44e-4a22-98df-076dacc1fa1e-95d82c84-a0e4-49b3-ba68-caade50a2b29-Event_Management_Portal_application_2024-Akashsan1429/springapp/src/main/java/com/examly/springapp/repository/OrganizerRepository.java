package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    // JPQL Query to find organizers by name
    @Query("SELECT o FROM Organizer o WHERE o.name = :name")
    List<Organizer> findByName(String name);

    // Pagination and Sorting
    Page<Organizer> findAll(Pageable pageable);
    boolean existsById(Long id);
}
