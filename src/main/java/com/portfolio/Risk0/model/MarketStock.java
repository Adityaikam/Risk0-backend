package com.portfolio.Risk0.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "market_stocks", indexes = {
        @Index(name = "idx_sector", columnList = "sector")
})
public class MarketStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String symbol;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(length = 50)
    private String sector;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "pe_ratio", precision = 10, scale = 2)
    private BigDecimal peRatio;

    @Column(name = "high_52week", precision = 15, scale = 2)
    private BigDecimal high52Week;

    @Column(name = "low_52week", precision = 15, scale = 2)
    private BigDecimal low52Week;

    private Long volume;

    @Column(precision = 10, scale = 2)
    private BigDecimal dividends;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
