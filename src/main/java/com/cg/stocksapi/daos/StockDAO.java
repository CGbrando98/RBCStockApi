package com.cg.stocksapi.daos;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.cg.stocksapi.models.*;

public interface StockDAO extends JpaRepository<Stock, Integer> {
	
	List<Stock> findAllBySymbol(String symbol);
}
