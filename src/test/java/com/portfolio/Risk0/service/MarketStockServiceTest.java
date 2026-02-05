package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.MarketStock;
import com.portfolio.Risk0.repository.MarketStockRepository;
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
class MarketStockServiceTest {

    @Mock
    private MarketStockRepository marketStockRepository;

    @InjectMocks
    private MarketStockService marketStockService;

    private MarketStock stock1;
    private MarketStock stock2;

    @BeforeEach
    void setUp() {
        stock1 = new MarketStock();
        stock1.setId(1L);
        stock1.setSymbol("AAPL");
        stock1.setCompanyName("Apple Inc.");
        stock1.setSector("Technology");
        stock1.setMarketCap(3000000000000L);
        stock1.setPeRatio(new BigDecimal("28.50"));
        stock1.setHigh52Week(new BigDecimal("200.00"));
        stock1.setLow52Week(new BigDecimal("120.00"));
        stock1.setVolume(50000000L);
        stock1.setDividends(new BigDecimal("0.96"));

        stock2 = new MarketStock();
        stock2.setId(2L);
        stock2.setSymbol("MSFT");
        stock2.setCompanyName("Microsoft Corporation");
        stock2.setSector("Technology");
        stock2.setMarketCap(2500000000000L);
    }

    @Test
    void testGetAllMarketStocks() {
        when(marketStockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<MarketStock> result = marketStockService.getAllMarketStocks();

        assertEquals(2, result.size());
        verify(marketStockRepository, times(1)).findAll();
    }

    @Test
    void testGetAllMarketStocksEmpty() {
        when(marketStockRepository.findAll()).thenReturn(List.of());

        List<MarketStock> result = marketStockService.getAllMarketStocks();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetMarketStockById() {
        when(marketStockRepository.findById(1L)).thenReturn(Optional.of(stock1));

        MarketStock result = marketStockService.getMarketStockById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("AAPL", result.getSymbol());
        verify(marketStockRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMarketStockByIdNotFound() {
        when(marketStockRepository.findById(999L)).thenReturn(Optional.empty());

        MarketStock result = marketStockService.getMarketStockById(999L);

        assertNull(result);
    }

    @Test
    void testGetMarketStockBySymbol() {
        when(marketStockRepository.findBySymbol("AAPL")).thenReturn(Optional.of(stock1));

        MarketStock result = marketStockService.getMarketStockBySymbol("AAPL");

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        assertEquals("Apple Inc.", result.getCompanyName());
        verify(marketStockRepository, times(1)).findBySymbol("AAPL");
    }

    @Test
    void testGetMarketStockBySymbolNotFound() {
        when(marketStockRepository.findBySymbol("INVALID")).thenReturn(Optional.empty());

        MarketStock result = marketStockService.getMarketStockBySymbol("INVALID");

        assertNull(result);
    }

    @Test
    void testGetMarketStocksBySector() {
        when(marketStockRepository.findBySector("Technology")).thenReturn(Arrays.asList(stock1, stock2));

        List<MarketStock> result = marketStockService.getMarketStocksBySector("Technology");

        assertEquals(2, result.size());
        verify(marketStockRepository, times(1)).findBySector("Technology");
    }

    @Test
    void testGetMarketStocksBySectorEmpty() {
        when(marketStockRepository.findBySector("Healthcare")).thenReturn(List.of());

        List<MarketStock> result = marketStockService.getMarketStocksBySector("Healthcare");

        assertTrue(result.isEmpty());
    }

    @Test
    void testAddMarketStock() {
        when(marketStockRepository.save(any(MarketStock.class))).thenReturn(stock1);

        MarketStock result = marketStockService.addMarketStock(stock1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        verify(marketStockRepository, times(1)).save(stock1);
    }

    @Test
    void testUpdateMarketStock() {
        MarketStock updatedStock = new MarketStock();
        updatedStock.setSymbol("AAPL");
        updatedStock.setCompanyName("Apple Inc. Updated");

        when(marketStockRepository.save(any(MarketStock.class))).thenAnswer(inv -> inv.getArgument(0));

        MarketStock result = marketStockService.updateMarketStock(1L, updatedStock);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Apple Inc. Updated", result.getCompanyName());
        verify(marketStockRepository, times(1)).save(updatedStock);
    }

    @Test
    void testDeleteMarketStock() {
        doNothing().when(marketStockRepository).deleteById(1L);

        marketStockService.deleteMarketStock(1L);

        verify(marketStockRepository, times(1)).deleteById(1L);
    }
}
