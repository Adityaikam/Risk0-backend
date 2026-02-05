package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LearningStatsTest {

    @Test
    void testNoArgsConstructor() {
        LearningStats stats = new LearningStats();
        assertNotNull(stats);
        assertNull(stats.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        LearningStats stats = new LearningStats(1L, user, new BigDecimal("50.5"),
                10, 5, 7, 10, 1500, 5, now);

        assertEquals(1L, stats.getId());
        assertEquals(user, stats.getUser());
        assertEquals(new BigDecimal("50.5"), stats.getTotalHours());
        assertEquals(10, stats.getCoursesCompleted());
        assertEquals(5, stats.getCertificatesEarned());
        assertEquals(7, stats.getCurrentStreak());
        assertEquals(10, stats.getBestStreak());
        assertEquals(1500, stats.getXpEarned());
        assertEquals(5, stats.getLevel());
    }

    @Test
    void testSettersAndGetters() {
        LearningStats stats = new LearningStats();
        User user = new User();
        user.setId(2L);

        stats.setId(1L);
        stats.setUser(user);
        stats.setTotalHours(new BigDecimal("25.75"));
        stats.setCoursesCompleted(5);
        stats.setCertificatesEarned(3);
        stats.setCurrentStreak(14);
        stats.setBestStreak(21);
        stats.setXpEarned(2500);
        stats.setLevel(8);

        assertEquals(1L, stats.getId());
        assertEquals(user, stats.getUser());
        assertEquals(new BigDecimal("25.75"), stats.getTotalHours());
        assertEquals(5, stats.getCoursesCompleted());
        assertEquals(3, stats.getCertificatesEarned());
        assertEquals(14, stats.getCurrentStreak());
        assertEquals(21, stats.getBestStreak());
        assertEquals(2500, stats.getXpEarned());
        assertEquals(8, stats.getLevel());
    }

    @Test
    void testDefaultValues() {
        LearningStats stats = new LearningStats();
        assertEquals(BigDecimal.ZERO, stats.getTotalHours());
        assertEquals(0, stats.getCoursesCompleted());
        assertEquals(0, stats.getCertificatesEarned());
        assertEquals(0, stats.getCurrentStreak());
        assertEquals(0, stats.getBestStreak());
        assertEquals(0, stats.getXpEarned());
        assertEquals(1, stats.getLevel());
    }

    @Test
    void testEqualsAndHashCode() {
        LearningStats stats1 = new LearningStats();
        stats1.setId(1L);
        stats1.setLevel(5);

        LearningStats stats2 = new LearningStats();
        stats2.setId(1L);
        stats2.setLevel(5);

        assertEquals(stats1, stats2);
        assertEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    void testNotEquals() {
        LearningStats stats1 = new LearningStats();
        stats1.setId(1L);

        LearningStats stats2 = new LearningStats();
        stats2.setId(2L);

        assertNotEquals(stats1, stats2);
    }

    @Test
    void testToString() {
        LearningStats stats = new LearningStats();
        stats.setId(1L);
        stats.setLevel(3);

        String toString = stats.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("LearningStats"));
    }
}
