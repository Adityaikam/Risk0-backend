package com.portfolio.Risk0.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlaskApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FlaskApiService flaskApiService;

    private static final String FLASK_API_URL = "http://localhost:5000/api/data";

    @Test
    void testGetStockDataSuccess() {
        String expectedData = "{\"symbol\": \"AAPL\", \"price\": 150.25}";
        when(restTemplate.getForObject(FLASK_API_URL, String.class)).thenReturn(expectedData);

        String result = flaskApiService.getStockData();

        assertEquals(expectedData, result);
        verify(restTemplate, times(1)).getForObject(FLASK_API_URL, String.class);
    }

    @Test
    void testGetStockDataReturnsNull() {
        when(restTemplate.getForObject(FLASK_API_URL, String.class)).thenReturn(null);

        String result = flaskApiService.getStockData();

        assertNull(result);
        verify(restTemplate, times(1)).getForObject(FLASK_API_URL, String.class);
    }

    @Test
    void testGetStockDataReturnsEmptyString() {
        when(restTemplate.getForObject(FLASK_API_URL, String.class)).thenReturn("");

        String result = flaskApiService.getStockData();

        assertEquals("", result);
        verify(restTemplate, times(1)).getForObject(FLASK_API_URL, String.class);
    }

    @Test
    void testGetStockDataWithComplexJson() {
        String complexData = "{\"stocks\": [{\"symbol\": \"AAPL\", \"price\": 150.25}, {\"symbol\": \"GOOGL\", \"price\": 2800.00}], \"timestamp\": \"2026-02-05T10:00:00Z\"}";
        when(restTemplate.getForObject(FLASK_API_URL, String.class)).thenReturn(complexData);

        String result = flaskApiService.getStockData();

        assertEquals(complexData, result);
        assertTrue(result.contains("AAPL"));
        assertTrue(result.contains("GOOGL"));
        assertTrue(result.contains("timestamp"));
        verify(restTemplate, times(1)).getForObject(FLASK_API_URL, String.class);
    }

    @Test
    void testGetStockDataThrowsException() {
        when(restTemplate.getForObject(FLASK_API_URL, String.class))
                .thenThrow(new RestClientException("Connection refused"));

        assertThrows(RestClientException.class, () -> flaskApiService.getStockData());
        verify(restTemplate, times(1)).getForObject(FLASK_API_URL, String.class);
    }
}
