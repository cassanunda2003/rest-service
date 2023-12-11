package com.example.restservice.service;

import com.example.restservice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LondonServiceTest {
    @Autowired
    private LondonService londonService;

    @Test
    void getUsers() {
        final List<User> users = londonService.getUsers();
        assertNotNull(users);


    }

    @Test
    void getUsersInLondon() {
        final List<User> users = londonService.getUsersInLondon();
        assertNotNull(users);
    }
}