package com.cg.stocksapi.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;

import com.cg.stocksapi.daos.*;
import com.cg.stocksapi.exceptions.*;
import com.cg.stocksapi.models.*;
import com.cg.stocksapi.services.*;

public class StockServicesTest {
	@Autowired 
	private StockServices stockServicesTest;
	
	@Mock 
	private StockDAO stockDaoTest;
	
	@Test
	void getStocksTest() throws StockNotFoundException {
		String symbol = "TTT";
		when(stockDaoTest.findAllBySymbol(symbol)).thenReturn(Stream.of(
				new Stock(0, 5,"Test",new Date (2011-01-07),Double.valueOf(15.87),16.,15.82,16.13,
						Double.valueOf(15.87),1.63831,9.355500109,Double.valueOf(15.87),16.18,17.14,5.93325,
						Double.valueOf(15.87),0.185989)).collect(Collectors.toList()));
		assertThat(stockServicesTest.getStocks(symbol).size()).isEqualTo(1);
	}
	
}
