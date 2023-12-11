package com.example.restservice.controller;

import com.example.restservice.domain.User;
import com.example.restservice.service.LondonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final LondonService londonService;

    @GetMapping("/users")
    public List<User> users() {
        return londonService.getUsers();
    }

    @GetMapping("/usersInLondon")
    public List<User> usersInLondon() {
        return londonService.getUsersInLondon();
    }
}
