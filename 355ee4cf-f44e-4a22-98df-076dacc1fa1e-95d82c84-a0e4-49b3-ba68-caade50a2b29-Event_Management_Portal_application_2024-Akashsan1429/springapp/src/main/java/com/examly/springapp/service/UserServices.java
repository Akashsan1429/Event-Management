package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.User;
import com.examly.springapp.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    public String addUser(User user) {
        if (repo.existsById(user.getId())) {
            return "User already exists!";
        }

        repo.save(user);
        return "User created successfully!";
    }

    

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(int id, User updatedUser) {
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if (!existingUser.getEmail().equals(updatedUser.getEmail()) && repo.existsByEmail(updatedUser.getEmail())) {
            throw new RuntimeException("Email already in use by another user");
        }

        updatedUser.setId(id);
        return repo.save(updatedUser);
    }

    public boolean deleteUser(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repo.deleteById(id);
        return true;
    }
    

    public List<User> getByUsername(String username) {
        return repo.findByUsernameContainingIgnoreCase(username);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        return repo.findUsersByEmailDomain(domain);
    }

    public Page<User> getUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    
}
