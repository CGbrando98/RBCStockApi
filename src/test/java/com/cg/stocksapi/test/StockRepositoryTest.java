package com.cg.stocksapi.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import com.cg.stocksapi.daos.*;
import com.cg.stocksapi.models.*;

@DataJpaTest
public class StockRepositoryTest {
	
	@Autowired
	private StockDAO stockDaoTest;
	
	@Test
	void findAllBySymbolTest() {
		List<Stock> storedStocks = new ArrayList<>();
		storedStocks.add(new Stock(0, 5,"Test",new Date (2011-01-07),Double.valueOf(15.87),16.,15.82,16.13,
				Double.valueOf(15.87),1.63831,9.355500109,Double.valueOf(15.87),16.18,17.14,5.93325,
				Double.valueOf(15.87),0.185989));
		storedStocks.add(new Stock(0, 5,"NonTest",new Date (2011-01-07),Double.valueOf(15.87),16.,15.82,16.13,
				Double.valueOf(15.87),1.63831,9.355500109,Double.valueOf(15.87),16.18,17.14,5.93325,
				Double.valueOf(15.87),0.185989));
		
		stockDaoTest.saveAll(storedStocks);
		
		String symbol = "Test";
		
		List<Stock> foundStocks =stockDaoTest.findAllBySymbol(symbol);
		assertThat(foundStocks.size()).isEqualTo(1);
	}
}
