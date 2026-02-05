package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WalletTransactionTest {

    @Test
    void testNoArgsConstructor() {
        WalletTransaction transaction = new WalletTransaction();
        assertNotNull(transaction);
        assertNull(transaction.getId());
    }

    @Test
    void testAllArgsConstructor() {
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        WalletTransaction transaction = new WalletTransaction(1L, wallet,
                WalletTransactionType.DEPOSIT, new BigDecimal("5000.00"),
                "Initial deposit", now);

        assertEquals(1L, transaction.getId());
        assertEquals(wallet, transaction.getWallet());
        assertEquals(WalletTransactionType.DEPOSIT, transaction.getTransactionType());
        assertEquals(new BigDecimal("5000.00"), transaction.getAmount());
        assertEquals("Initial deposit", transaction.getDescription());
        assertEquals(now, transaction.getTransactionDate());
    }

    @Test
    void testSettersAndGetters() {
        WalletTransaction transaction = new WalletTransaction();
        Wallet wallet = new Wallet();
        wallet.setId(2L);
        LocalDateTime transactionDate = LocalDateTime.of(2025, 11, 15, 14, 30);

        transaction.setId(1L);
        transaction.setWallet(wallet);
        transaction.setTransactionType(WalletTransactionType.WITHDRAWAL);
        transaction.setAmount(new BigDecimal("2500.00"));
        transaction.setDescription("ATM withdrawal");
        transaction.setTransactionDate(transactionDate);

        assertEquals(1L, transaction.getId());
        assertEquals(wallet, transaction.getWallet());
        assertEquals(WalletTransactionType.WITHDRAWAL, transaction.getTransactionType());
        assertEquals(new BigDecimal("2500.00"), transaction.getAmount());
        assertEquals("ATM withdrawal", transaction.getDescription());
        assertEquals(transactionDate, transaction.getTransactionDate());
    }

    @Test
    void testEqualsAndHashCode() {
        WalletTransaction trans1 = new WalletTransaction();
        trans1.setId(1L);
        trans1.setAmount(new BigDecimal("1000.00"));

        WalletTransaction trans2 = new WalletTransaction();
        trans2.setId(1L);
        trans2.setAmount(new BigDecimal("1000.00"));

        assertEquals(trans1, trans2);
        assertEquals(trans1.hashCode(), trans2.hashCode());
    }

    @Test
    void testNotEquals() {
        WalletTransaction trans1 = new WalletTransaction();
        trans1.setId(1L);

        WalletTransaction trans2 = new WalletTransaction();
        trans2.setId(2L);

        assertNotEquals(trans1, trans2);
    }

    @Test
    void testToString() {
        WalletTransaction transaction = new WalletTransaction();
        transaction.setId(1L);
        transaction.setTransactionType(WalletTransactionType.DEPOSIT);

        String toString = transaction.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("WalletTransaction"));
    }
}
