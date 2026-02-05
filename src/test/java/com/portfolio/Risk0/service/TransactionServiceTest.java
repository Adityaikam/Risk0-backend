package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.Transaction;
import com.portfolio.Risk0.model.TransactionType;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.repository.TransactionRepository;
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
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction1;
    private Transaction transaction2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setUser(user);
        transaction1.setSymbol("AAPL");
        transaction1.setTransactionType(TransactionType.BUY);
        transaction1.setQuantity(10);
        transaction1.setPrice(new BigDecimal("150.00"));
        transaction1.setTotalAmount(new BigDecimal("1500.00"));

        transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setUser(user);
        transaction2.setSymbol("GOOGL");
        transaction2.setTransactionType(TransactionType.SELL);
        transaction2.setQuantity(5);
        transaction2.setPrice(new BigDecimal("2800.00"));
        transaction2.setTotalAmount(new BigDecimal("14000.00"));
    }

    @Test
    void testGetAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetAllTransactionsEmpty() {
        when(transactionRepository.findAll()).thenReturn(List.of());

        List<Transaction> result = transactionService.getAllTransactions();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTransactionsBySymbolAndUserId() {
        when(transactionRepository.findByUserIdAndSymbol(1L, "AAPL")).thenReturn(List.of(transaction1));

        List<Transaction> result = transactionService.getTransactionsBySymbol("AAPL", 1L);

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
        verify(transactionRepository, times(1)).findByUserIdAndSymbol(1L, "AAPL");
    }

    @Test
    void testGetTransactionsByUserIdOnly() {
        when(transactionRepository.findByUserId(1L)).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionService.getTransactionsBySymbol(null, 1L);

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetTransactionsBySymbolNullUserId() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionService.getTransactionsBySymbol("AAPL", null);

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionsByBothNull() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionService.getTransactionsBySymbol(null, null);

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testAddTransaction() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction1);

        Transaction result = transactionService.addTransaction(transaction1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        assertEquals(TransactionType.BUY, result.getTransactionType());
        verify(transactionRepository, times(1)).save(transaction1);
    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction1));

        Transaction result = transactionService.getTransactionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("AAPL", result.getSymbol());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        when(transactionRepository.findById(999L)).thenReturn(Optional.empty());

        Transaction result = transactionService.getTransactionById(999L);

        assertNull(result);
    }

    @Test
    void testDeleteTransaction() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }
}
