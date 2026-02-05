package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.MarketStock;
import com.portfolio.Risk0.service.MarketStockService;
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
class MarketStockControllerTest {

    @Mock
    private MarketStockService marketStockService;

    @InjectMocks
    private MarketStockController marketStockController;

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

        stock2 = new MarketStock();
        stock2.setId(2L);
        stock2.setSymbol("MSFT");
        stock2.setCompanyName("Microsoft Corporation");
        stock2.setSector("Technology");
    }

    @Test
    void testGetAllMarketStocks() {
        when(marketStockService.getAllMarketStocks()).thenReturn(Arrays.asList(stock1, stock2));

        List<MarketStock> result = marketStockController.getAllMarketStocks(null);

        assertEquals(2, result.size());
        verify(marketStockService, times(1)).getAllMarketStocks();
    }

    @Test
    void testGetAllMarketStocksBySector() {
        when(marketStockService.getMarketStocksBySector("Technology")).thenReturn(Arrays.asList(stock1, stock2));

        List<MarketStock> result = marketStockController.getAllMarketStocks("Technology");

        assertEquals(2, result.size());
        verify(marketStockService, times(1)).getMarketStocksBySector("Technology");
        verify(marketStockService, never()).getAllMarketStocks();
    }

    @Test
    void testGetAllMarketStocksEmpty() {
        when(marketStockService.getAllMarketStocks()).thenReturn(List.of());

        List<MarketStock> result = marketStockController.getAllMarketStocks(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetMarketStockById() {
        when(marketStockService.getMarketStockById(1L)).thenReturn(stock1);

        MarketStock result = marketStockController.getMarketStockById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("AAPL", result.getSymbol());
        verify(marketStockService, times(1)).getMarketStockById(1L);
    }

    @Test
    void testGetMarketStockByIdNotFound() {
        when(marketStockService.getMarketStockById(999L)).thenReturn(null);

        MarketStock result = marketStockController.getMarketStockById(999L);

        assertNull(result);
    }

    @Test
    void testGetMarketStockBySymbol() {
        when(marketStockService.getMarketStockBySymbol("AAPL")).thenReturn(stock1);

        MarketStock result = marketStockController.getMarketStockBySymbol("AAPL");

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        assertEquals("Apple Inc.", result.getCompanyName());
        verify(marketStockService, times(1)).getMarketStockBySymbol("AAPL");
    }

    @Test
    void testGetMarketStockBySymbolNotFound() {
        when(marketStockService.getMarketStockBySymbol("INVALID")).thenReturn(null);

        MarketStock result = marketStockController.getMarketStockBySymbol("INVALID");

        assertNull(result);
    }

    @Test
    void testAddMarketStock() {
        when(marketStockService.addMarketStock(any(MarketStock.class))).thenReturn(stock1);

        MarketStock result = marketStockController.addMarketStock(stock1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
        verify(marketStockService, times(1)).addMarketStock(stock1);
    }

    @Test
    void testUpdateMarketStock() {
        MarketStock updatedStock = new MarketStock();
        updatedStock.setSymbol("AAPL");
        updatedStock.setCompanyName("Apple Inc. Updated");

        when(marketStockService.updateMarketStock(eq(1L), any(MarketStock.class))).thenReturn(updatedStock);

        MarketStock result = marketStockController.updateMarketStock(1L, updatedStock);

        assertNotNull(result);
        assertEquals("Apple Inc. Updated", result.getCompanyName());
        verify(marketStockService, times(1)).updateMarketStock(1L, updatedStock);
    }

    @Test
    void testDeleteMarketStock() {
        doNothing().when(marketStockService).deleteMarketStock(1L);

        marketStockController.deleteMarketStock(1L);

        verify(marketStockService, times(1)).deleteMarketStock(1L);
    }
}
