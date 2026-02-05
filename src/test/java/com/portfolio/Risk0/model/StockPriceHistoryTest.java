package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StockPriceHistoryTest {

    @Test
    void testNoArgsConstructor() {
        StockPriceHistory history = new StockPriceHistory();
        assertNotNull(history);
        assertNull(history.getId());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();

        StockPriceHistory history = new StockPriceHistory(1L, "AAPL",
                new BigDecimal("150.00"), now);

        assertEquals(1L, history.getId());
        assertEquals("AAPL", history.getSymbol());
        assertEquals(new BigDecimal("150.00"), history.getPrice());
        assertEquals(now, history.getRecordedAt());
    }

    @Test
    void testSettersAndGetters() {
        StockPriceHistory history = new StockPriceHistory();
        LocalDateTime recordedAt = LocalDateTime.of(2025, 12, 1, 10, 30);

        history.setId(1L);
        history.setSymbol("GOOGL");
        history.setPrice(new BigDecimal("2800.50"));
        history.setRecordedAt(recordedAt);

        assertEquals(1L, history.getId());
        assertEquals("GOOGL", history.getSymbol());
        assertEquals(new BigDecimal("2800.50"), history.getPrice());
        assertEquals(recordedAt, history.getRecordedAt());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();

        StockPriceHistory history1 = new StockPriceHistory();
        history1.setId(1L);
        history1.setSymbol("AAPL");
        history1.setPrice(new BigDecimal("150.00"));
        history1.setRecordedAt(now);

        StockPriceHistory history2 = new StockPriceHistory();
        history2.setId(1L);
        history2.setSymbol("AAPL");
        history2.setPrice(new BigDecimal("150.00"));
        history2.setRecordedAt(now);

        assertEquals(history1, history2);
        assertEquals(history1.hashCode(), history2.hashCode());
    }

    @Test
    void testNotEquals() {
        StockPriceHistory history1 = new StockPriceHistory();
        history1.setId(1L);

        StockPriceHistory history2 = new StockPriceHistory();
        history2.setId(2L);

        assertNotEquals(history1, history2);
    }

    @Test
    void testToString() {
        StockPriceHistory history = new StockPriceHistory();
        history.setId(1L);
        history.setSymbol("MSFT");

        String toString = history.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("StockPriceHistory"));
    }
}
