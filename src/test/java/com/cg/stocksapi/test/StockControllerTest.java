package com.cg.stocksapi.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.junit.jupiter.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.context.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;

import com.cg.stocksapi.controllers.*;
import com.cg.stocksapi.services.*;
import com.cg.stocksapi.utils.*;


@WebMvcTest(StockController.class)
//@WebAppConfiguration()
//@AutoConfigureMockMvc
public class StockControllerTest {
	@MockBean
	private StockServices stockServicesTest;
	
	@MockBean
	private ReadFile readFileServicesTest; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void searchStocksTest () throws Exception {
		mockMvc.perform(get("/stocks/search")
			    .param("ticker", "TTT"))
			    .andExpect(status().isOk());
	}
}
