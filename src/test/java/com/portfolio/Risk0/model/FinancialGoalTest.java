package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FinancialGoalTest {

    @Test
    void testNoArgsConstructor() {
        FinancialGoal goal = new FinancialGoal();
        assertNotNull(goal);
        assertNull(goal.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        LocalDate deadline = LocalDate.now().plusMonths(6);

        FinancialGoal goal = new FinancialGoal(1L, user, "Retirement Fund", "Savings",
                new BigDecimal("100000.00"), new BigDecimal("25000.00"), deadline, now, now);

        assertEquals(1L, goal.getId());
        assertEquals(user, goal.getUser());
        assertEquals("Retirement Fund", goal.getGoalName());
        assertEquals("Savings", goal.getGoalType());
        assertEquals(new BigDecimal("100000.00"), goal.getTargetAmount());
        assertEquals(new BigDecimal("25000.00"), goal.getCurrentAmount());
        assertEquals(deadline, goal.getDeadline());
    }

    @Test
    void testSettersAndGetters() {
        FinancialGoal goal = new FinancialGoal();
        User user = new User();
        user.setId(2L);
        LocalDate deadline = LocalDate.of(2027, 1, 1);

        goal.setId(1L);
        goal.setUser(user);
        goal.setGoalName("Emergency Fund");
        goal.setGoalType("Emergency");
        goal.setTargetAmount(new BigDecimal("50000.00"));
        goal.setCurrentAmount(new BigDecimal("10000.00"));
        goal.setDeadline(deadline);

        assertEquals(1L, goal.getId());
        assertEquals(user, goal.getUser());
        assertEquals("Emergency Fund", goal.getGoalName());
        assertEquals("Emergency", goal.getGoalType());
        assertEquals(new BigDecimal("50000.00"), goal.getTargetAmount());
        assertEquals(new BigDecimal("10000.00"), goal.getCurrentAmount());
        assertEquals(deadline, goal.getDeadline());
    }

    @Test
    void testDefaultCurrentAmount() {
        FinancialGoal goal = new FinancialGoal();
        assertEquals(BigDecimal.ZERO, goal.getCurrentAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        FinancialGoal goal1 = new FinancialGoal();
        goal1.setId(1L);
        goal1.setGoalName("Test Goal");

        FinancialGoal goal2 = new FinancialGoal();
        goal2.setId(1L);
        goal2.setGoalName("Test Goal");

        assertEquals(goal1, goal2);
        assertEquals(goal1.hashCode(), goal2.hashCode());
    }

    @Test
    void testNotEquals() {
        FinancialGoal goal1 = new FinancialGoal();
        goal1.setId(1L);

        FinancialGoal goal2 = new FinancialGoal();
        goal2.setId(2L);

        assertNotEquals(goal1, goal2);
    }

    @Test
    void testToString() {
        FinancialGoal goal = new FinancialGoal();
        goal.setId(1L);
        goal.setGoalName("Test");

        String toString = goal.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("FinancialGoal"));
    }
}
