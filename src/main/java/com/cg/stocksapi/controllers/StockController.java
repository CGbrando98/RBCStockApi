package com.cg.stocksapi.controllers;

import java.io.*;
import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.cg.stocksapi.exceptions.*;
import com.cg.stocksapi.models.*;
import com.cg.stocksapi.services.*;
import com.cg.stocksapi.utils.*;

@RestController
public class StockController {
	// magical instantiation
		@Autowired
		private StockServices stockServices;
		
		// using a separate bean for reading file
		@Autowired
		private ReadFile readFileServices;
		
		@PostMapping("/stocks")
		public ResponseEntity<String> addStock(@RequestBody Stock stock) {
			stockServices.saveStock(stock);
			return new ResponseEntity<>("Stock details added", HttpStatus.OK);
		}
		
		@GetMapping("/stocks/search")
		public ResponseEntity<List<Stock>> searchStocks(@RequestParam(name="ticker") String ticker) throws StockNotFoundException {
			List<Stock> stocks = stockServices.getStocks(ticker);
			return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);
		}
		
		@DeleteMapping("/stocks/{stockid}")
		public ResponseEntity<String> removeStock( @PathVariable int stockid) throws StockNotFoundException{
			stockServices.deleteStock(stockid);
			return new ResponseEntity<>("Stock deleted", HttpStatus.OK);
		}
		
		@PostMapping("/stocks/bulk")
		public ResponseEntity<String> addStocks(@RequestBody List<Stock> stocks) {
			stockServices.saveBulkStocks(stocks);
			return new ResponseEntity<>("Stocks added", HttpStatus.OK);
		}
		
		@PostMapping("/stocks/bulk/file")
		public ResponseEntity<String> addStocksFile(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
			readFileServices.saveStockFromUploadFile(file);
			return new ResponseEntity<>("Stocks added", HttpStatus.OK);
		}
}
