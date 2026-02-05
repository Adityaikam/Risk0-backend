package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.FinancialGoal;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.repository.FinancialGoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinancialGoalServiceTest {

    @Mock
    private FinancialGoalRepository financialGoalRepository;

    @InjectMocks
    private FinancialGoalService financialGoalService;

    private FinancialGoal goal1;
    private FinancialGoal goal2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        goal1 = new FinancialGoal();
        goal1.setId(1L);
        goal1.setUser(user);
        goal1.setGoalName("Retirement Fund");
        goal1.setGoalType("Savings");
        goal1.setTargetAmount(new BigDecimal("100000.00"));
        goal1.setCurrentAmount(new BigDecimal("25000.00"));
        goal1.setDeadline(LocalDate.of(2030, 12, 31));

        goal2 = new FinancialGoal();
        goal2.setId(2L);
        goal2.setUser(user);
        goal2.setGoalName("Emergency Fund");
        goal2.setGoalType("Emergency");
        goal2.setTargetAmount(new BigDecimal("50000.00"));
        goal2.setCurrentAmount(new BigDecimal("10000.00"));
    }

    @Test
    void testGetAllFinancialGoals() {
        when(financialGoalRepository.findAll()).thenReturn(Arrays.asList(goal1, goal2));

        List<FinancialGoal> result = financialGoalService.getAllFinancialGoals();

        assertEquals(2, result.size());
        verify(financialGoalRepository, times(1)).findAll();
    }

    @Test
    void testGetAllFinancialGoalsEmpty() {
        when(financialGoalRepository.findAll()).thenReturn(List.of());

        List<FinancialGoal> result = financialGoalService.getAllFinancialGoals();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetFinancialGoalsByUserId() {
        when(financialGoalRepository.findByUserId(1L)).thenReturn(Arrays.asList(goal1, goal2));

        List<FinancialGoal> result = financialGoalService.getFinancialGoalsByUserId(1L);

        assertEquals(2, result.size());
        verify(financialGoalRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetFinancialGoalsByUserIdEmpty() {
        when(financialGoalRepository.findByUserId(999L)).thenReturn(List.of());

        List<FinancialGoal> result = financialGoalService.getFinancialGoalsByUserId(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetFinancialGoalById() {
        when(financialGoalRepository.findById(1L)).thenReturn(Optional.of(goal1));

        FinancialGoal result = financialGoalService.getFinancialGoalById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Retirement Fund", result.getGoalName());
        verify(financialGoalRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFinancialGoalByIdNotFound() {
        when(financialGoalRepository.findById(999L)).thenReturn(Optional.empty());

        FinancialGoal result = financialGoalService.getFinancialGoalById(999L);

        assertNull(result);
    }

    @Test
    void testAddFinancialGoal() {
        when(financialGoalRepository.save(any(FinancialGoal.class))).thenReturn(goal1);

        FinancialGoal result = financialGoalService.addFinancialGoal(goal1);

        assertNotNull(result);
        assertEquals("Retirement Fund", result.getGoalName());
        verify(financialGoalRepository, times(1)).save(goal1);
    }

    @Test
    void testUpdateFinancialGoal() {
        FinancialGoal updatedGoal = new FinancialGoal();
        updatedGoal.setGoalName("Updated Goal");
        updatedGoal.setTargetAmount(new BigDecimal("200000.00"));

        when(financialGoalRepository.save(any(FinancialGoal.class))).thenAnswer(invocation -> {
            FinancialGoal saved = invocation.getArgument(0);
            return saved;
        });

        FinancialGoal result = financialGoalService.updateFinancialGoal(1L, updatedGoal);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Goal", result.getGoalName());
        verify(financialGoalRepository, times(1)).save(updatedGoal);
    }

    @Test
    void testDeleteFinancialGoal() {
        doNothing().when(financialGoalRepository).deleteById(1L);

        financialGoalService.deleteFinancialGoal(1L);

        verify(financialGoalRepository, times(1)).deleteById(1L);
    }
}
