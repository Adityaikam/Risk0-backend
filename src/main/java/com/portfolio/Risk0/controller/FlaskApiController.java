package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.service.FlaskApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flask")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FlaskApiController {

    @Autowired
    private FlaskApiService flaskApiService;

    @GetMapping("/stock-data")
    public String getStockData() {
        return flaskApiService.getStockData();
    }
}
