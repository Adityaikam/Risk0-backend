package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.service.FlaskApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlaskApiControllerTest {

    @Mock
    private FlaskApiService flaskApiService;

    @InjectMocks
    private FlaskApiController flaskApiController;

    @Test
    void testGetStockData() {
        String expectedData = "{\"symbol\": \"AAPL\", \"price\": 150.25}";
        when(flaskApiService.getStockData()).thenReturn(expectedData);

        String result = flaskApiController.getStockData();

        assertEquals(expectedData, result);
        verify(flaskApiService, times(1)).getStockData();
    }

    @Test
    void testGetStockDataReturnsNull() {
        when(flaskApiService.getStockData()).thenReturn(null);

        String result = flaskApiController.getStockData();

        assertNull(result);
        verify(flaskApiService, times(1)).getStockData();
    }

    @Test
    void testGetStockDataReturnsEmptyString() {
        when(flaskApiService.getStockData()).thenReturn("");

        String result = flaskApiController.getStockData();

        assertEquals("", result);
        verify(flaskApiService, times(1)).getStockData();
    }

    @Test
    void testGetStockDataReturnsComplexJson() {
        String complexData = "{\"stocks\": [{\"symbol\": \"AAPL\", \"price\": 150.25}, {\"symbol\": \"GOOGL\", \"price\": 2800.00}]}";
        when(flaskApiService.getStockData()).thenReturn(complexData);

        String result = flaskApiController.getStockData();

        assertEquals(complexData, result);
        assertTrue(result.contains("AAPL"));
        assertTrue(result.contains("GOOGL"));
        verify(flaskApiService, times(1)).getStockData();
    }
}
