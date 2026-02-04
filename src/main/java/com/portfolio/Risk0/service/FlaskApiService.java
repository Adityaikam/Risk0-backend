package com.portfolio.Risk0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskApiService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String FLASK_API_URL = "http://localhost:5000/api/data";

    public String getStockData() {
        return restTemplate.getForObject(FLASK_API_URL, String.class);
    }
}
