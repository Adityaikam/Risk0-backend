package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.Transaction;
import com.portfolio.Risk0.model.TransactionType;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.service.TransactionService;
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
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private Transaction transaction1;
    private Transaction transaction2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

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
        when(transactionService.getTransactionsBySymbol(null, null)).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionController.getAllTransactions(null, null);

        assertEquals(2, result.size());
        verify(transactionService, times(1)).getTransactionsBySymbol(null, null);
    }

    @Test
    void testGetAllTransactionsWithSymbol() {
        when(transactionService.getTransactionsBySymbol("AAPL", null)).thenReturn(List.of(transaction1));

        List<Transaction> result = transactionController.getAllTransactions("AAPL", null);

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
        verify(transactionService, times(1)).getTransactionsBySymbol("AAPL", null);
    }

    @Test
    void testGetAllTransactionsWithUserId() {
        when(transactionService.getTransactionsBySymbol(null, 1L)).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> result = transactionController.getAllTransactions(null, 1L);

        assertEquals(2, result.size());
        verify(transactionService, times(1)).getTransactionsBySymbol(null, 1L);
    }

    @Test
    void testGetAllTransactionsWithSymbolAndUserId() {
        when(transactionService.getTransactionsBySymbol("AAPL", 1L)).thenReturn(List.of(transaction1));

        List<Transaction> result = transactionController.getAllTransactions("AAPL", 1L);

        assertEquals(1, result.size());
        verify(transactionService, times(1)).getTransactionsBySymbol("AAPL", 1L);
    }

    @Test
    void testGetTransactionById() {
        when(transactionService.getTransactionById(1L)).thenReturn(transaction1);

        Transaction result = transactionController.getTransactionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("AAPL", result.getSymbol());
        assertEquals(TransactionType.BUY, result.getTransactionType());
        verify(transactionService, times(1)).getTransactionById(1L);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        when(transactionService.getTransactionById(999L)).thenReturn(null);

        Transaction result = transactionController.getTransactionById(999L);

        assertNull(result);
    }

    @Test
    void testAddTransaction() {
        when(transactionService.addTransaction(any(Transaction.class))).thenReturn(transaction1);

        Transaction result = transactionController.addTransaction(transaction1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        assertEquals(TransactionType.BUY, result.getTransactionType());
        verify(transactionService, times(1)).addTransaction(transaction1);
    }

    @Test
    void testDeleteTransaction() {
        doNothing().when(transactionService).deleteTransaction(1L);

        transactionController.deleteTransaction(1L);

        verify(transactionService, times(1)).deleteTransaction(1L);
    }
}
