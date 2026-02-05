package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testNoArgsConstructor() {
        Transaction transaction = new Transaction();
        assertNotNull(transaction);
        assertNull(transaction.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        Transaction transaction = new Transaction(1L, user, "AAPL", TransactionType.BUY,
                10, new BigDecimal("150.00"), new BigDecimal("1500.00"), now);

        assertEquals(1L, transaction.getId());
        assertEquals(user, transaction.getUser());
        assertEquals("AAPL", transaction.getSymbol());
        assertEquals(TransactionType.BUY, transaction.getTransactionType());
        assertEquals(10, transaction.getQuantity());
        assertEquals(new BigDecimal("150.00"), transaction.getPrice());
        assertEquals(new BigDecimal("1500.00"), transaction.getTotalAmount());
        assertEquals(now, transaction.getTransactionDate());
    }

    @Test
    void testSettersAndGetters() {
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(2L);
        LocalDateTime transactionDate = LocalDateTime.of(2025, 11, 15, 14, 30);

        transaction.setId(1L);
        transaction.setUser(user);
        transaction.setSymbol("GOOGL");
        transaction.setTransactionType(TransactionType.SELL);
        transaction.setQuantity(5);
        transaction.setPrice(new BigDecimal("2800.00"));
        transaction.setTotalAmount(new BigDecimal("14000.00"));
        transaction.setTransactionDate(transactionDate);

        assertEquals(1L, transaction.getId());
        assertEquals(user, transaction.getUser());
        assertEquals("GOOGL", transaction.getSymbol());
        assertEquals(TransactionType.SELL, transaction.getTransactionType());
        assertEquals(5, transaction.getQuantity());
        assertEquals(new BigDecimal("2800.00"), transaction.getPrice());
        assertEquals(new BigDecimal("14000.00"), transaction.getTotalAmount());
        assertEquals(transactionDate, transaction.getTransactionDate());
    }

    @Test
    void testEqualsAndHashCode() {
        Transaction trans1 = new Transaction();
        trans1.setId(1L);
        trans1.setSymbol("AAPL");
        trans1.setTransactionType(TransactionType.BUY);

        Transaction trans2 = new Transaction();
        trans2.setId(1L);
        trans2.setSymbol("AAPL");
        trans2.setTransactionType(TransactionType.BUY);

        assertEquals(trans1, trans2);
        assertEquals(trans1.hashCode(), trans2.hashCode());
    }

    @Test
    void testNotEquals() {
        Transaction trans1 = new Transaction();
        trans1.setId(1L);

        Transaction trans2 = new Transaction();
        trans2.setId(2L);

        assertNotEquals(trans1, trans2);
    }

    @Test
    void testToString() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setSymbol("TSLA");

        String toString = transaction.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Transaction"));
    }
}
