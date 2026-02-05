package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTransactionTypeTest {

    @Test
    void testDepositValue() {
        WalletTransactionType type = WalletTransactionType.DEPOSIT;
        assertEquals("DEPOSIT", type.name());
    }

    @Test
    void testWithdrawalValue() {
        WalletTransactionType type = WalletTransactionType.WITHDRAWAL;
        assertEquals("WITHDRAWAL", type.name());
    }

    @Test
    void testValuesCount() {
        WalletTransactionType[] values = WalletTransactionType.values();
        assertEquals(2, values.length);
    }

    @Test
    void testValueOf() {
        assertEquals(WalletTransactionType.DEPOSIT, WalletTransactionType.valueOf("DEPOSIT"));
        assertEquals(WalletTransactionType.WITHDRAWAL, WalletTransactionType.valueOf("WITHDRAWAL"));
    }

    @Test
    void testValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            WalletTransactionType.valueOf("INVALID");
        });
    }

    @Test
    void testOrdinal() {
        assertEquals(0, WalletTransactionType.DEPOSIT.ordinal());
        assertEquals(1, WalletTransactionType.WITHDRAWAL.ordinal());
    }
}
