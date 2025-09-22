package com.example.hiRecipedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @PostMapping("/addd")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role) {

        if (userDetailsManager.userExists(username)) {
            return "User is already exists!";
        }

        UserDetails newUser = User.withUsername(username)
                .password("{noop}" + password) // No encoding for testing only
                .roles(role.toUpperCase())
                .build();

        userDetailsManager.createUser(newUser);
        return "User added!";
    }

    @GetMapping
    public String m(){
        return "Welcome";
    }
}
