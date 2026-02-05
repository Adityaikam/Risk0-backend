package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.model.Wallet;
import com.portfolio.Risk0.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    private Wallet wallet1;
    private Wallet wallet2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        wallet1 = new Wallet();
        wallet1.setId(1L);
        wallet1.setUser(user);
        wallet1.setWalletName("Main Wallet");
        wallet1.setBankName("HDFC Bank");
        wallet1.setAccountNumber("1234567890");
        wallet1.setBalance(new BigDecimal("50000.00"));
        wallet1.setWalletType("Savings");

        wallet2 = new Wallet();
        wallet2.setId(2L);
        wallet2.setUser(user);
        wallet2.setWalletName("Trading Account");
        wallet2.setBankName("ICICI Bank");
        wallet2.setBalance(new BigDecimal("100000.00"));
    }

    @Test
    void testGetAllWallets() {
        when(walletService.getAllWallets()).thenReturn(Arrays.asList(wallet1, wallet2));

        List<Wallet> result = walletController.getAllWallets(null);

        assertEquals(2, result.size());
        verify(walletService, times(1)).getAllWallets();
    }

    @Test
    void testGetAllWalletsByUserId() {
        when(walletService.getWalletsByUserId(1L)).thenReturn(Arrays.asList(wallet1, wallet2));

        List<Wallet> result = walletController.getAllWallets(1L);

        assertEquals(2, result.size());
        verify(walletService, times(1)).getWalletsByUserId(1L);
        verify(walletService, never()).getAllWallets();
    }

    @Test
    void testGetAllWalletsEmpty() {
        when(walletService.getAllWallets()).thenReturn(List.of());

        List<Wallet> result = walletController.getAllWallets(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWalletById() {
        when(walletService.getWalletById(1L)).thenReturn(wallet1);

        Wallet result = walletController.getWalletById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Main Wallet", result.getWalletName());
        assertEquals("HDFC Bank", result.getBankName());
        verify(walletService, times(1)).getWalletById(1L);
    }

    @Test
    void testGetWalletByIdNotFound() {
        when(walletService.getWalletById(999L)).thenReturn(null);

        Wallet result = walletController.getWalletById(999L);

        assertNull(result);
    }

    @Test
    void testAddWallet() {
        when(walletService.addWallet(any(Wallet.class))).thenReturn(wallet1);

        Wallet result = walletController.addWallet(wallet1);

        assertNotNull(result);
        assertEquals("Main Wallet", result.getWalletName());
        assertEquals("HDFC Bank", result.getBankName());
        verify(walletService, times(1)).addWallet(wallet1);
    }

    @Test
    void testUpdateWallet() {
        Wallet updatedWallet = new Wallet();
        updatedWallet.setWalletName("Updated Wallet");
        updatedWallet.setBankName("SBI");

        when(walletService.updateWallet(eq(1L), any(Wallet.class))).thenReturn(updatedWallet);

        Wallet result = walletController.updateWallet(1L, updatedWallet);

        assertNotNull(result);
        assertEquals("Updated Wallet", result.getWalletName());
        assertEquals("SBI", result.getBankName());
        verify(walletService, times(1)).updateWallet(1L, updatedWallet);
    }

    @Test
    void testDeleteWallet() {
        doNothing().when(walletService).deleteWallet(1L);

        walletController.deleteWallet(1L);

        verify(walletService, times(1)).deleteWallet(1L);
    }
}
