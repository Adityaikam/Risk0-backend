package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.Holding;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.service.HoldingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HoldingControllerTest {

    @Mock
    private HoldingService holdingService;

    @InjectMocks
    private HoldingController holdingController;

    private Holding holding1;
    private Holding holding2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        holding1 = new Holding();
        holding1.setId(1L);
        holding1.setUser(user);
        holding1.setSymbol("AAPL");
        holding1.setCompanyName("Apple Inc.");
        holding1.setSector("Technology");
        holding1.setCurrentPrice(new BigDecimal("150.00"));
        holding1.setTimePeriod("1M");
        holding1.setQuantity(10);

        holding2 = new Holding();
        holding2.setId(2L);
        holding2.setUser(user);
        holding2.setSymbol("GOOGL");
        holding2.setCompanyName("Alphabet Inc.");
        holding2.setTimePeriod("3M");
    }

    @Test
    void testGetAllHoldings() {
        when(holdingService.getAllHoldings(null)).thenReturn(Arrays.asList(holding1, holding2));

        ResponseEntity<List<Holding>> result = holdingController.getAllHoldings(null);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(holdingService, times(1)).getAllHoldings(null);
    }

    @Test
    void testGetAllHoldingsWithTimePeriod() {
        when(holdingService.getAllHoldings("1M")).thenReturn(List.of(holding1));

        ResponseEntity<List<Holding>> result = holdingController.getAllHoldings("1M");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals("AAPL", result.getBody().get(0).getSymbol());
        verify(holdingService, times(1)).getAllHoldings("1M");
    }

    @Test
    void testGetAllHoldingsEmpty() {
        when(holdingService.getAllHoldings(null)).thenReturn(List.of());

        ResponseEntity<List<Holding>> result = holdingController.getAllHoldings(null);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
    }

    @Test
    void testCreateHolding() {
        when(holdingService.createHolding(any(Holding.class))).thenReturn(holding1);

        ResponseEntity<Holding> result = holdingController.createHolding(holding1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("AAPL", result.getBody().getSymbol());
        verify(holdingService, times(1)).createHolding(holding1);
    }

    @Test
    void testUpdateHoldingSuccess() {
        when(holdingService.updateHolding(eq(1L), any(Holding.class))).thenReturn(Optional.of(holding1));

        ResponseEntity<Holding> result = holdingController.updateHolding(1L, holding1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("AAPL", result.getBody().getSymbol());
        verify(holdingService, times(1)).updateHolding(1L, holding1);
    }

    @Test
    void testUpdateHoldingNotFound() {
        when(holdingService.updateHolding(eq(999L), any(Holding.class))).thenReturn(Optional.empty());

        ResponseEntity<Holding> result = holdingController.updateHolding(999L, holding1);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void testDeleteHoldingSuccess() {
        when(holdingService.deleteHolding(1L)).thenReturn(true);

        ResponseEntity<Void> result = holdingController.deleteHolding(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(holdingService, times(1)).deleteHolding(1L);
    }

    @Test
    void testDeleteHoldingNotFound() {
        when(holdingService.deleteHolding(999L)).thenReturn(false);

        ResponseEntity<Void> result = holdingController.deleteHolding(999L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
