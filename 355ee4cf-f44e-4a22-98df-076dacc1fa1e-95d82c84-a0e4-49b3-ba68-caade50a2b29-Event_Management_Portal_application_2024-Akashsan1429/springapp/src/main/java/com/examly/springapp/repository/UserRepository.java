package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameContainingIgnoreCase(String username);
    Optional<User> findByEmail(String email);
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // JPQL query to fetch users by email domain
    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
    List<User> findUsersByEmailDomain(String domain);

    // Pagination and sorting
    Page<User> findAll(Pageable pageable);
}