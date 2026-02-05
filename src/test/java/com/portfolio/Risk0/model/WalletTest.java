package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void testNoArgsConstructor() {
        Wallet wallet = new Wallet();
        assertNotNull(wallet);
        assertNull(wallet.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        Wallet wallet = new Wallet(1L, user, "Main Wallet", "HDFC Bank",
                "1234567890", new BigDecimal("50000.00"), "Savings", now, now);

        assertEquals(1L, wallet.getId());
        assertEquals(user, wallet.getUser());
        assertEquals("Main Wallet", wallet.getWalletName());
        assertEquals("HDFC Bank", wallet.getBankName());
        assertEquals("1234567890", wallet.getAccountNumber());
        assertEquals(new BigDecimal("50000.00"), wallet.getBalance());
        assertEquals("Savings", wallet.getWalletType());
        assertEquals(now, wallet.getCreatedAt());
        assertEquals(now, wallet.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        Wallet wallet = new Wallet();
        User user = new User();
        user.setId(2L);
        LocalDateTime createdAt = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 11, 15, 14, 30);

        wallet.setId(1L);
        wallet.setUser(user);
        wallet.setWalletName("Trading Account");
        wallet.setBankName("ICICI Bank");
        wallet.setAccountNumber("9876543210");
        wallet.setBalance(new BigDecimal("100000.00"));
        wallet.setWalletType("Trading");
        wallet.setCreatedAt(createdAt);
        wallet.setUpdatedAt(updatedAt);

        assertEquals(1L, wallet.getId());
        assertEquals(user, wallet.getUser());
        assertEquals("Trading Account", wallet.getWalletName());
        assertEquals("ICICI Bank", wallet.getBankName());
        assertEquals("9876543210", wallet.getAccountNumber());
        assertEquals(new BigDecimal("100000.00"), wallet.getBalance());
        assertEquals("Trading", wallet.getWalletType());
        assertEquals(createdAt, wallet.getCreatedAt());
        assertEquals(updatedAt, wallet.getUpdatedAt());
    }

    @Test
    void testDefaultBalance() {
        Wallet wallet = new Wallet();
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    @Test
    void testEqualsAndHashCode() {
        Wallet wallet1 = new Wallet();
        wallet1.setId(1L);
        wallet1.setWalletName("Test Wallet");

        Wallet wallet2 = new Wallet();
        wallet2.setId(1L);
        wallet2.setWalletName("Test Wallet");

        assertEquals(wallet1, wallet2);
        assertEquals(wallet1.hashCode(), wallet2.hashCode());
    }

    @Test
    void testNotEquals() {
        Wallet wallet1 = new Wallet();
        wallet1.setId(1L);

        Wallet wallet2 = new Wallet();
        wallet2.setId(2L);

        assertNotEquals(wallet1, wallet2);
    }

    @Test
    void testToString() {
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setWalletName("My Wallet");

        String toString = wallet.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Wallet"));
    }
}
