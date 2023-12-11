package com.example.restservice.service;

import com.example.restservice.domain.User;
import com.example.restservice.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LondonService {

    private final RestTemplateBuilder restTemplateBuilder;

    private final static String ALL_USERS = "/users";
    private final static String LONDON_USERS = "/city/London/users";
    public List<User> getUserList(String url) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<User[]> response =
                restTemplate.getForEntity(
                        url,
                        User[].class);
        User[] users = response.getBody();
        return Arrays.stream(Objects.requireNonNull(users)).toList();
    }

    public List<User> getUsers() {
        return getUserList(LONDON_USERS);
    }
    public List<User> getUsersInLondon() {
        return getUserList(ALL_USERS).stream().filter(c -> DistanceCalculator.withinGivenMilesOfLondon(50, c.getLatitude(), c.getLongitude())).toList();
    }
}
