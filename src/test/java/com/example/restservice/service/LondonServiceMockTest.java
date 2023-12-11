package com.example.restservice.service;

import com.example.restservice.config.RestTemplateBuilderConfig;
import com.example.restservice.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest
@Import(RestTemplateBuilderConfig.class)
class LondonServiceMockTest {

    static final String URL = "http://localhost:8080";

    private LondonService londonService;

    MockRestServiceServer server;

    @Autowired
    RestTemplateBuilder restTemplateBuilderConfigured;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    RestTemplateBuilder mockRestTemplateBuilder = new RestTemplateBuilder(new MockServerRestTemplateCustomizer());

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = restTemplateBuilderConfigured.build();
        server = MockRestServiceServer.bindTo(restTemplate).build();
        when(mockRestTemplateBuilder.build()).thenReturn(restTemplate);
        londonService = new LondonService(mockRestTemplateBuilder);
    }
    @Test
    void getUsers() {

        String payload = "[{\"id\":135,\"first_name\":\"Mike\",\"last_name\":\"Boam\",\"email\":\"mboam3q@thetimes.co.uk\",\"ip_address\":\"113.71.242.187\",\"latitude\":-6.5115909,\"longitude\":105.652983}]";
        server.expect(method(HttpMethod.GET)).andExpect(requestTo("https://london-api.onrender.com/city/London/users"))
                .andRespond(withSuccess(payload, MediaType.APPLICATION_JSON));
        final List<User> users = londonService.getUsers();
        System.out.println(users.toString());
        assertNotNull(users);


    }

    @Test
    void getUsersInLondon() {
        String payload ="[{\"id\":135,\"first_name\":\"John\",\"last_name\":\"Boam\",\"email\":\"john3boam@thetimes.co.uk\",\"ip_address\":\"113.71.242.185\",\"latitude\":51.50722,\"longitude\":-0.1275}]";
        server.expect(method(HttpMethod.GET)).andExpect(requestTo("https://london-api.onrender.com/users"))
                .andRespond(withSuccess(payload, MediaType.APPLICATION_JSON));
        final List<User> users = londonService.getUsersInLondon();
        System.out.println(users.toString());
        assertNotNull(users);
    }
}