package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MarketStockTest {

    @Test
    void testNoArgsConstructor() {
        MarketStock stock = new MarketStock();
        assertNotNull(stock);
        assertNull(stock.getId());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();

        MarketStock stock = new MarketStock(1L, "AAPL", "Apple Inc.", "Technology",
                3000000000000L, new BigDecimal("28.50"), new BigDecimal("200.00"),
                new BigDecimal("120.00"), 50000000L, new BigDecimal("0.96"), now);

        assertEquals(1L, stock.getId());
        assertEquals("AAPL", stock.getSymbol());
        assertEquals("Apple Inc.", stock.getCompanyName());
        assertEquals("Technology", stock.getSector());
        assertEquals(3000000000000L, stock.getMarketCap());
        assertEquals(new BigDecimal("28.50"), stock.getPeRatio());
        assertEquals(new BigDecimal("200.00"), stock.getHigh52Week());
        assertEquals(new BigDecimal("120.00"), stock.getLow52Week());
        assertEquals(50000000L, stock.getVolume());
        assertEquals(new BigDecimal("0.96"), stock.getDividends());
    }

    @Test
    void testSettersAndGetters() {
        MarketStock stock = new MarketStock();

        stock.setId(1L);
        stock.setSymbol("MSFT");
        stock.setCompanyName("Microsoft Corporation");
        stock.setSector("Technology");
        stock.setMarketCap(2500000000000L);
        stock.setPeRatio(new BigDecimal("35.20"));
        stock.setHigh52Week(new BigDecimal("380.00"));
        stock.setLow52Week(new BigDecimal("250.00"));
        stock.setVolume(30000000L);
        stock.setDividends(new BigDecimal("2.80"));

        assertEquals(1L, stock.getId());
        assertEquals("MSFT", stock.getSymbol());
        assertEquals("Microsoft Corporation", stock.getCompanyName());
        assertEquals("Technology", stock.getSector());
        assertEquals(2500000000000L, stock.getMarketCap());
        assertEquals(new BigDecimal("35.20"), stock.getPeRatio());
        assertEquals(new BigDecimal("380.00"), stock.getHigh52Week());
        assertEquals(new BigDecimal("250.00"), stock.getLow52Week());
        assertEquals(30000000L, stock.getVolume());
        assertEquals(new BigDecimal("2.80"), stock.getDividends());
    }

    @Test
    void testEqualsAndHashCode() {
        MarketStock stock1 = new MarketStock();
        stock1.setId(1L);
        stock1.setSymbol("AAPL");

        MarketStock stock2 = new MarketStock();
        stock2.setId(1L);
        stock2.setSymbol("AAPL");

        assertEquals(stock1, stock2);
        assertEquals(stock1.hashCode(), stock2.hashCode());
    }

    @Test
    void testNotEquals() {
        MarketStock stock1 = new MarketStock();
        stock1.setId(1L);

        MarketStock stock2 = new MarketStock();
        stock2.setId(2L);

        assertNotEquals(stock1, stock2);
    }

    @Test
    void testToString() {
        MarketStock stock = new MarketStock();
        stock.setId(1L);
        stock.setSymbol("TSLA");

        String toString = stock.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("MarketStock"));
    }
}
