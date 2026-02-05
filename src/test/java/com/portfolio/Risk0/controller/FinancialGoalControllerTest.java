package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.FinancialGoal;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.service.FinancialGoalService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinancialGoalControllerTest {

    @Mock
    private FinancialGoalService financialGoalService;

    @InjectMocks
    private FinancialGoalController financialGoalController;

    private FinancialGoal goal1;
    private FinancialGoal goal2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

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
        goal2.setTargetAmount(new BigDecimal("50000.00"));
    }

    @Test
    void testGetAllFinancialGoals() {
        when(financialGoalService.getAllFinancialGoals()).thenReturn(Arrays.asList(goal1, goal2));

        List<FinancialGoal> result = financialGoalController.getAllFinancialGoals(null);

        assertEquals(2, result.size());
        verify(financialGoalService, times(1)).getAllFinancialGoals();
    }

    @Test
    void testGetAllFinancialGoalsByUserId() {
        when(financialGoalService.getFinancialGoalsByUserId(1L)).thenReturn(Arrays.asList(goal1, goal2));

        List<FinancialGoal> result = financialGoalController.getAllFinancialGoals(1L);

        assertEquals(2, result.size());
        verify(financialGoalService, times(1)).getFinancialGoalsByUserId(1L);
        verify(financialGoalService, never()).getAllFinancialGoals();
    }

    @Test
    void testGetFinancialGoalById() {
        when(financialGoalService.getFinancialGoalById(1L)).thenReturn(goal1);

        FinancialGoal result = financialGoalController.getFinancialGoalById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Retirement Fund", result.getGoalName());
        verify(financialGoalService, times(1)).getFinancialGoalById(1L);
    }

    @Test
    void testGetFinancialGoalByIdNotFound() {
        when(financialGoalService.getFinancialGoalById(999L)).thenReturn(null);

        FinancialGoal result = financialGoalController.getFinancialGoalById(999L);

        assertNull(result);
    }

    @Test
    void testAddFinancialGoal() {
        when(financialGoalService.addFinancialGoal(any(FinancialGoal.class))).thenReturn(goal1);

        FinancialGoal result = financialGoalController.addFinancialGoal(goal1);

        assertNotNull(result);
        assertEquals("Retirement Fund", result.getGoalName());
        verify(financialGoalService, times(1)).addFinancialGoal(goal1);
    }

    @Test
    void testUpdateFinancialGoal() {
        FinancialGoal updatedGoal = new FinancialGoal();
        updatedGoal.setGoalName("Updated Goal");

        when(financialGoalService.updateFinancialGoal(eq(1L), any(FinancialGoal.class))).thenReturn(updatedGoal);

        FinancialGoal result = financialGoalController.updateFinancialGoal(1L, updatedGoal);

        assertNotNull(result);
        assertEquals("Updated Goal", result.getGoalName());
        verify(financialGoalService, times(1)).updateFinancialGoal(1L, updatedGoal);
    }

    @Test
    void testDeleteFinancialGoal() {
        doNothing().when(financialGoalService).deleteFinancialGoal(1L);

        financialGoalController.deleteFinancialGoal(1L);

        verify(financialGoalService, times(1)).deleteFinancialGoal(1L);
    }
}
