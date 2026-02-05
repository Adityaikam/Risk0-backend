package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.Holding;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.repository.HoldingRepository;
import com.portfolio.Risk0.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HoldingServiceTest {

    @Mock
    private HoldingRepository holdingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HoldingService holdingService;

    private Holding holding1;
    private Holding holding2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        holding1 = new Holding();
        holding1.setId(1L);
        holding1.setUser(user);
        holding1.setSymbol("AAPL");
        holding1.setCompanyName("Apple Inc.");
        holding1.setSector("Technology");
        holding1.setCurrentPrice(new BigDecimal("150.00"));
        holding1.setAcquiredPrice(new BigDecimal("140.00"));
        holding1.setTimePeriod("1M");
        holding1.setQuantity(10);
        holding1.setTotalInvested(new BigDecimal("1400.00"));
        holding1.setAcquiredDate(LocalDate.now().minusMonths(1));

        holding2 = new Holding();
        holding2.setId(2L);
        holding2.setUser(user);
        holding2.setSymbol("GOOGL");
        holding2.setCompanyName("Alphabet Inc.");
        holding2.setSector("Technology");
        holding2.setCurrentPrice(new BigDecimal("2800.00"));
        holding2.setTimePeriod("3M");
        holding2.setQuantity(5);
    }

    @Test
    void testGetAllHoldingsWithTimePeriod() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(holdingRepository.findByUserIdAndTimePeriod(1L, "1M")).thenReturn(List.of(holding1));

        List<Holding> result = holdingService.getAllHoldings("1M");

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
        verify(holdingRepository, times(1)).findByUserIdAndTimePeriod(1L, "1M");
    }

    @Test
    void testGetAllHoldingsWithoutTimePeriod() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(holdingRepository.findByUserId(1L)).thenReturn(Arrays.asList(holding1, holding2));

        List<Holding> result = holdingService.getAllHoldings(null);

        assertEquals(2, result.size());
        verify(holdingRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetAllHoldingsEmptyTimePeriod() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(holdingRepository.findByUserId(1L)).thenReturn(Arrays.asList(holding1, holding2));

        List<Holding> result = holdingService.getAllHoldings("");

        assertEquals(2, result.size());
        verify(holdingRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetAllHoldingsNoUser() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<Holding> result = holdingService.getAllHoldings("1M");

        assertTrue(result.isEmpty());
        verify(holdingRepository, never()).findByUserId(anyLong());
    }

    @Test
    void testCreateHolding() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(holdingRepository.save(any(Holding.class))).thenReturn(holding1);

        Holding result = holdingService.createHolding(holding1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        verify(holdingRepository, times(1)).save(holding1);
    }

    @Test
    void testCreateHoldingNoUser() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> holdingService.createHolding(holding1));
    }

    @Test
    void testUpdateHolding() {
        Holding existingHolding = new Holding();
        existingHolding.setId(1L);
        existingHolding.setSymbol("OLD");

        when(holdingRepository.findById(1L)).thenReturn(Optional.of(existingHolding));
        when(holdingRepository.save(any(Holding.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<Holding> result = holdingService.updateHolding(1L, holding1);

        assertTrue(result.isPresent());
        assertEquals("AAPL", result.get().getSymbol());
        assertEquals("Apple Inc.", result.get().getCompanyName());
        verify(holdingRepository, times(1)).save(existingHolding);
    }

    @Test
    void testUpdateHoldingNotFound() {
        when(holdingRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Holding> result = holdingService.updateHolding(999L, holding1);

        assertTrue(result.isEmpty());
        verify(holdingRepository, never()).save(any());
    }

    @Test
    void testDeleteHoldingSuccess() {
        when(holdingRepository.existsById(1L)).thenReturn(true);
        doNothing().when(holdingRepository).deleteById(1L);

        boolean result = holdingService.deleteHolding(1L);

        assertTrue(result);
        verify(holdingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteHoldingNotFound() {
        when(holdingRepository.existsById(999L)).thenReturn(false);

        boolean result = holdingService.deleteHolding(999L);

        assertFalse(result);
        verify(holdingRepository, never()).deleteById(anyLong());
    }
}
