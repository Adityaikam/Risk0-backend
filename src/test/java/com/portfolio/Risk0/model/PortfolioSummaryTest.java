package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioSummaryTest {

    @Test
    void testNoArgsConstructor() {
        PortfolioSummary summary = new PortfolioSummary();
        assertNotNull(summary);
        assertNull(summary.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        PortfolioSummary summary = new PortfolioSummary(1L, user,
                new BigDecimal("100000.00"), new BigDecimal("120000.00"),
                new BigDecimal("20000.00"), now);

        assertEquals(1L, summary.getId());
        assertEquals(user, summary.getUser());
        assertEquals(new BigDecimal("100000.00"), summary.getTotalInvested());
        assertEquals(new BigDecimal("120000.00"), summary.getCurrentValue());
        assertEquals(new BigDecimal("20000.00"), summary.getTotalReturns());
    }

    @Test
    void testSettersAndGetters() {
        PortfolioSummary summary = new PortfolioSummary();
        User user = new User();
        user.setId(2L);

        summary.setId(1L);
        summary.setUser(user);
        summary.setTotalInvested(new BigDecimal("50000.00"));
        summary.setCurrentValue(new BigDecimal("55000.00"));
        summary.setTotalReturns(new BigDecimal("5000.00"));

        assertEquals(1L, summary.getId());
        assertEquals(user, summary.getUser());
        assertEquals(new BigDecimal("50000.00"), summary.getTotalInvested());
        assertEquals(new BigDecimal("55000.00"), summary.getCurrentValue());
        assertEquals(new BigDecimal("5000.00"), summary.getTotalReturns());
    }

    @Test
    void testEqualsAndHashCode() {
        PortfolioSummary summary1 = new PortfolioSummary();
        summary1.setId(1L);
        summary1.setTotalInvested(new BigDecimal("10000.00"));

        PortfolioSummary summary2 = new PortfolioSummary();
        summary2.setId(1L);
        summary2.setTotalInvested(new BigDecimal("10000.00"));

        assertEquals(summary1, summary2);
        assertEquals(summary1.hashCode(), summary2.hashCode());
    }

    @Test
    void testNotEquals() {
        PortfolioSummary summary1 = new PortfolioSummary();
        summary1.setId(1L);

        PortfolioSummary summary2 = new PortfolioSummary();
        summary2.setId(2L);

        assertNotEquals(summary1, summary2);
    }

    @Test
    void testToString() {
        PortfolioSummary summary = new PortfolioSummary();
        summary.setId(1L);

        String toString = summary.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("PortfolioSummary"));
    }
}
