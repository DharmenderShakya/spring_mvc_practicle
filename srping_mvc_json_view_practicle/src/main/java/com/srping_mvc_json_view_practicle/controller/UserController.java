package com.srping_mvc_json_view_practicle.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.request.UserRequest;
import com.srping_mvc_json_view_practicle.service.UserService;
import com.srping_mvc_json_view_practicle.view.Views;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users (NO orders)
    @GetMapping("/getAllUser")
    @JsonView(Views.UserSummary.class)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user with orders
    @GetMapping("/{id}")
    @JsonView(Views.UserDetails.class)
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Create user
    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserRequest user) {
        return userService.createUser(user);
    }

    // Update user
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody UserRequest user) {
        return userService.updateUser(user);
    }

    //  Delete user
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
