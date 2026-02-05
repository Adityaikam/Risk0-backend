package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTypeTest {

    @Test
    void testBuyValue() {
        TransactionType type = TransactionType.BUY;
        assertEquals("BUY", type.name());
    }

    @Test
    void testSellValue() {
        TransactionType type = TransactionType.SELL;
        assertEquals("SELL", type.name());
    }

    @Test
    void testValuesCount() {
        TransactionType[] values = TransactionType.values();
        assertEquals(2, values.length);
    }

    @Test
    void testValueOf() {
        assertEquals(TransactionType.BUY, TransactionType.valueOf("BUY"));
        assertEquals(TransactionType.SELL, TransactionType.valueOf("SELL"));
    }

    @Test
    void testValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            TransactionType.valueOf("INVALID");
        });
    }

    @Test
    void testOrdinal() {
        assertEquals(0, TransactionType.BUY.ordinal());
        assertEquals(1, TransactionType.SELL.ordinal());
    }
}
