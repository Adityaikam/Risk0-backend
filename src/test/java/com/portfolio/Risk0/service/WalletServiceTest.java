package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.model.Wallet;
import com.portfolio.Risk0.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

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
        wallet2.setWalletType("Trading");
    }

    @Test
    void testGetAllWallets() {
        when(walletRepository.findAll()).thenReturn(Arrays.asList(wallet1, wallet2));

        List<Wallet> result = walletService.getAllWallets();

        assertEquals(2, result.size());
        verify(walletRepository, times(1)).findAll();
    }

    @Test
    void testGetAllWalletsEmpty() {
        when(walletRepository.findAll()).thenReturn(List.of());

        List<Wallet> result = walletService.getAllWallets();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWalletsByUserId() {
        when(walletRepository.findByUserId(1L)).thenReturn(Arrays.asList(wallet1, wallet2));

        List<Wallet> result = walletService.getWalletsByUserId(1L);

        assertEquals(2, result.size());
        verify(walletRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetWalletsByUserIdEmpty() {
        when(walletRepository.findByUserId(999L)).thenReturn(List.of());

        List<Wallet> result = walletService.getWalletsByUserId(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWalletById() {
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet1));

        Wallet result = walletService.getWalletById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Main Wallet", result.getWalletName());
        verify(walletRepository, times(1)).findById(1L);
    }

    @Test
    void testGetWalletByIdNotFound() {
        when(walletRepository.findById(999L)).thenReturn(Optional.empty());

        Wallet result = walletService.getWalletById(999L);

        assertNull(result);
    }

    @Test
    void testAddWallet() {
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet1);

        Wallet result = walletService.addWallet(wallet1);

        assertNotNull(result);
        assertEquals("Main Wallet", result.getWalletName());
        assertEquals("HDFC Bank", result.getBankName());
        verify(walletRepository, times(1)).save(wallet1);
    }

    @Test
    void testUpdateWallet() {
        Wallet updatedWallet = new Wallet();
        updatedWallet.setWalletName("Updated Wallet");
        updatedWallet.setBankName("SBI");
        updatedWallet.setBalance(new BigDecimal("75000.00"));

        when(walletRepository.save(any(Wallet.class))).thenAnswer(inv -> inv.getArgument(0));

        Wallet result = walletService.updateWallet(1L, updatedWallet);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Wallet", result.getWalletName());
        assertEquals("SBI", result.getBankName());
        verify(walletRepository, times(1)).save(updatedWallet);
    }

    @Test
    void testDeleteWallet() {
        doNothing().when(walletRepository).deleteById(1L);

        walletService.deleteWallet(1L);

        verify(walletRepository, times(1)).deleteById(1L);
    }
}
