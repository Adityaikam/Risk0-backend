package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentCategoryTest {

    @Test
    void testNoArgsConstructor() {
        InvestmentCategory category = new InvestmentCategory();
        assertNotNull(category);
        assertNull(category.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        InvestmentCategory category = new InvestmentCategory(1L, user, "Stocks",
                new BigDecimal("10000.00"), new BigDecimal("12000.00"), 5, now);

        assertEquals(1L, category.getId());
        assertEquals(user, category.getUser());
        assertEquals("Stocks", category.getCategoryType());
        assertEquals(new BigDecimal("10000.00"), category.getTotalInvested());
        assertEquals(new BigDecimal("12000.00"), category.getCurrentValue());
        assertEquals(5, category.getHoldingsCount());
    }

    @Test
    void testSettersAndGetters() {
        InvestmentCategory category = new InvestmentCategory();
        User user = new User();
        user.setId(2L);

        category.setId(1L);
        category.setUser(user);
        category.setCategoryType("Bonds");
        category.setTotalInvested(new BigDecimal("5000.00"));
        category.setCurrentValue(new BigDecimal("5200.00"));
        category.setHoldingsCount(3);

        assertEquals(1L, category.getId());
        assertEquals(user, category.getUser());
        assertEquals("Bonds", category.getCategoryType());
        assertEquals(new BigDecimal("5000.00"), category.getTotalInvested());
        assertEquals(new BigDecimal("5200.00"), category.getCurrentValue());
        assertEquals(3, category.getHoldingsCount());
    }

    @Test
    void testDefaultValues() {
        InvestmentCategory category = new InvestmentCategory();
        assertEquals(BigDecimal.ZERO, category.getTotalInvested());
        assertEquals(BigDecimal.ZERO, category.getCurrentValue());
        assertEquals(0, category.getHoldingsCount());
    }

    @Test
    void testEqualsAndHashCode() {
        InvestmentCategory cat1 = new InvestmentCategory();
        cat1.setId(1L);
        cat1.setCategoryType("Stocks");

        InvestmentCategory cat2 = new InvestmentCategory();
        cat2.setId(1L);
        cat2.setCategoryType("Stocks");

        assertEquals(cat1, cat2);
        assertEquals(cat1.hashCode(), cat2.hashCode());
    }

    @Test
    void testNotEquals() {
        InvestmentCategory cat1 = new InvestmentCategory();
        cat1.setId(1L);

        InvestmentCategory cat2 = new InvestmentCategory();
        cat2.setId(2L);

        assertNotEquals(cat1, cat2);
    }

    @Test
    void testToString() {
        InvestmentCategory category = new InvestmentCategory();
        category.setId(1L);
        category.setCategoryType("ETF");

        String toString = category.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("InvestmentCategory"));
    }
}
