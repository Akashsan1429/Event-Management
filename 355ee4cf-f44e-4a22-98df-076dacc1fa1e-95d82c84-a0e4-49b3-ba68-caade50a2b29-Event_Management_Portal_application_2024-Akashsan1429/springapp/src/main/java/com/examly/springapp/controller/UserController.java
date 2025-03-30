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

import com.examly.springapp.entity.User;
import com.examly.springapp.service.UserServices;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @PostMapping
    public String addUser(@Valid @RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
        return service.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id) ? "User Deleted" : "User Not Found";
    }

    @GetMapping("/username")
    public List<User> getUsersByUsername(@RequestParam String username) {
        return service.getByUsername(username);
    }

    @GetMapping("/domain")
    public List<User> getUsersByEmailDomain(@RequestParam String domain) {
        return service.getUsersByEmailDomain(domain);
    }

    @GetMapping("/page")
    public Page<User> getUsersPaginated(@RequestParam int page, @RequestParam int size) {
        return service.getUsersPaginated(page, size);
    }
}