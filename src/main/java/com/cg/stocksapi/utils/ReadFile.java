package com.cg.stocksapi.utils;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

import javax.annotation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.cg.stocksapi.daos.*;
import com.cg.stocksapi.models.*;

@Service
public class ReadFile {
	
	@Autowired
	private StockDAO stockDao;
	
	public void saveStockFromUploadFile (MultipartFile file) throws IOException, ParseException {
		InputStream inputStream = file.getInputStream();
		List<String> fileLines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines().collect(Collectors.toList());
		saveFunc(fileLines);
	}
	
	//@PostConstruct
	public void saveStockFromLocalFile() throws IOException, ParseException {
		List<String> fileLines = Files.readAllLines(Path.of("dow_jones_index.data"));
		saveFunc(fileLines);
	}
	
	public void saveFunc(List<String> fileData) throws ParseException {
	fileData.remove(0);
	for (String line : fileData) {
	List<String> lineSplit = Arrays.asList(line.split(","));
	
		// remove the $ from the prices
		for (int i = 0; i < lineSplit.size(); i++) {
			if (lineSplit.get(i)!= "") {
		    	if (lineSplit.get(i).charAt(0) == '$') {
		    		lineSplit.set(i, lineSplit.get(i).substring(1));
		    	} 
			}
		}
	
		int tempQuarter =Integer.valueOf(lineSplit.get(0));
		String tempSymbol = lineSplit.get(1);
		Date tempDate = new SimpleDateFormat("MM/dd/yyyy").parse(lineSplit.get(2));
		Double tempOpen = convertToDouble(lineSplit.get(3));
		Double tempHigh = convertToDouble(lineSplit.get(4));
		Double tempLow = convertToDouble(lineSplit.get(5));
		Double tempClose = convertToDouble(lineSplit.get(6));
		Double tempVolume = convertToDouble(lineSplit.get(7));
		Double tempPercent_change_price = convertToDouble(lineSplit.get(8));
		Double tempPercent_change_volume_over_last_wk =convertToDouble(lineSplit.get(9));
		Double tempPrevious_weeks_volume = convertToDouble(lineSplit.get(10));
		Double tempNext_weeks_open = convertToDouble(lineSplit.get(11));
		Double tempNext_weeks_close = convertToDouble(lineSplit.get(12));
		Double tempPercent_change_next_weeks_price = convertToDouble(lineSplit.get(13));
		Double tempDays_to_next_dividend = convertToDouble(lineSplit.get(14));
		Double tempPercent_return_next_dividend = convertToDouble(lineSplit.get(15));
		
		Stock stock = new Stock(0, tempQuarter, tempSymbol,tempDate, tempOpen, tempHigh, tempLow,
				tempClose, tempVolume, tempPercent_change_price, tempPercent_change_volume_over_last_wk, 
				tempPrevious_weeks_volume, tempNext_weeks_open, tempNext_weeks_close, 
				tempPercent_change_next_weeks_price, tempDays_to_next_dividend,
				tempPercent_return_next_dividend);
		stockDao.save(stock);
	}
	
	}
	Double convertToDouble(String input) {
		if (input == "") {
			return null;
		}
		else 
			return Double.valueOf(input);
	}
}


	
	
	
