package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // JPQL Query to find events by location
    @Query("SELECT e FROM Event e WHERE e.location = :location")
    List<Event> findByLocation(String location);

    // Pagination and Sorting
    Page<Event> findAll(Pageable pageable);

    //Custom Query with JPQL for fetching events by organizer ID
    @Query("SELECT e FROM Event e WHERE e.organizer.id = :organizerId")
    List<Event> findEventsByOrganizerId(int organizerId);
    boolean existsById(Long id);
    List<Event> findEventsByOrganizerName(String name);
    List<Event> findEventsByVenueId(int venueId);
    void deleteByOrganizerId(int organizerId);
    void deleteByVenueId(int venueId);
    List<Event> findEventsByUserId(int id);

}
