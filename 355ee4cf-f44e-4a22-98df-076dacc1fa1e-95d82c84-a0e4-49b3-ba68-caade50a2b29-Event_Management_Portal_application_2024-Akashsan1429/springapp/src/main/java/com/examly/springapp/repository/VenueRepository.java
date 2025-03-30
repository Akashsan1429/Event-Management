package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    boolean existsById(Integer id);
    boolean existsByName(String name);

    // JPQL Query to find Venues by location
    @Query("SELECT v FROM Venue v WHERE v.location = :location")
    List<Venue> findByLocation(String location);

    // Pagination and Sorting
    Page<Venue> findAll(Pageable pageable);
}
