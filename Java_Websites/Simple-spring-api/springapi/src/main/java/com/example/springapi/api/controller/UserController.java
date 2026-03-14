package com.example.springapi.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.model.User;
import com.example.springapi.service.UserService;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/user") // Meghívja az api beállítást.
    public User getUser(@RequestParam Integer id) {

        Optional user = userService.getUser(id);

        if (user.isPresent()) {

            return (User) user.get();
        }

        return null; // Nem található ilyen felhasználó.
    }

}
