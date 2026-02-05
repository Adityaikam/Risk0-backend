package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HoldingTest {

    @Test
    void testNoArgsConstructor() {
        Holding holding = new Holding();
        assertNotNull(holding);
        assertNull(holding.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        LocalDate acquiredDate = LocalDate.now().minusMonths(1);

        Holding holding = new Holding(1L, user, "AAPL", "Apple Inc.", "Technology",
                new BigDecimal("150.00"), new BigDecimal("140.00"), "1M",
                10, new BigDecimal("1400.00"), acquiredDate, now);

        assertEquals(1L, holding.getId());
        assertEquals(user, holding.getUser());
        assertEquals("AAPL", holding.getSymbol());
        assertEquals("Apple Inc.", holding.getCompanyName());
        assertEquals("Technology", holding.getSector());
        assertEquals(new BigDecimal("150.00"), holding.getCurrentPrice());
        assertEquals(new BigDecimal("140.00"), holding.getAcquiredPrice());
        assertEquals("1M", holding.getTimePeriod());
        assertEquals(10, holding.getQuantity());
        assertEquals(new BigDecimal("1400.00"), holding.getTotalInvested());
        assertEquals(acquiredDate, holding.getAcquiredDate());
    }

    @Test
    void testSettersAndGetters() {
        Holding holding = new Holding();
        User user = new User();
        user.setId(2L);
        LocalDate acquiredDate = LocalDate.of(2025, 6, 15);

        holding.setId(1L);
        holding.setUser(user);
        holding.setSymbol("GOOGL");
        holding.setCompanyName("Alphabet Inc.");
        holding.setSector("Technology");
        holding.setCurrentPrice(new BigDecimal("2800.00"));
        holding.setAcquiredPrice(new BigDecimal("2600.00"));
        holding.setTimePeriod("3M");
        holding.setQuantity(5);
        holding.setTotalInvested(new BigDecimal("13000.00"));
        holding.setAcquiredDate(acquiredDate);

        assertEquals(1L, holding.getId());
        assertEquals(user, holding.getUser());
        assertEquals("GOOGL", holding.getSymbol());
        assertEquals("Alphabet Inc.", holding.getCompanyName());
        assertEquals("Technology", holding.getSector());
        assertEquals(new BigDecimal("2800.00"), holding.getCurrentPrice());
        assertEquals(new BigDecimal("2600.00"), holding.getAcquiredPrice());
        assertEquals("3M", holding.getTimePeriod());
        assertEquals(5, holding.getQuantity());
        assertEquals(new BigDecimal("13000.00"), holding.getTotalInvested());
        assertEquals(acquiredDate, holding.getAcquiredDate());
    }

    @Test
    void testEqualsAndHashCode() {
        Holding holding1 = new Holding();
        holding1.setId(1L);
        holding1.setSymbol("AAPL");

        Holding holding2 = new Holding();
        holding2.setId(1L);
        holding2.setSymbol("AAPL");

        assertEquals(holding1, holding2);
        assertEquals(holding1.hashCode(), holding2.hashCode());
    }

    @Test
    void testNotEquals() {
        Holding holding1 = new Holding();
        holding1.setId(1L);

        Holding holding2 = new Holding();
        holding2.setId(2L);

        assertNotEquals(holding1, holding2);
    }

    @Test
    void testToString() {
        Holding holding = new Holding();
        holding.setId(1L);
        holding.setSymbol("TSLA");

        String toString = holding.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Holding"));
    }
}
