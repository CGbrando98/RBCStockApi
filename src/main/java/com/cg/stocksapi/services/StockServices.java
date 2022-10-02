package com.cg.stocksapi.services;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.cg.stocksapi.daos.*;
import com.cg.stocksapi.exceptions.*;
import com.cg.stocksapi.models.*;


@Service
public class StockServices {
	
	@Autowired
	private StockDAO stockDao;
	
	
	public void saveStock(Stock stock) {
		try {
			System.out.println(stock);
			stockDao.save(stock);
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Error in saving");
		}
	}
	
	public List<Stock> getStocks(String ticker) throws StockNotFoundException {
			System.out.println(ticker);
			List<Stock> stocksList = stockDao.findAllBySymbol(ticker);
			return stocksList;
	}
	
	public void deleteStock(int id) throws StockNotFoundException {
		try {
			stockDao.deleteById(id);
		}
		catch (Exception e) {
			throw new StockNotFoundException("Could not find Stock to delete with "
					+ "id: "+ id);
		}
	}
	
	public void saveBulkStocks(List<Stock> stocks) {
		try {
			stockDao.saveAll(stocks);
		} catch (Exception e) {
			System.err.println("Error in saving bulk records");
		}
	}
}
