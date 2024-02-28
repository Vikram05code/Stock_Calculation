package com.vikram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.client.StockClient;

@RestController
@RequestMapping("/calc")
public class StockCalculationRestController {

	private StockClient stockClient;
	
	@GetMapping("/calculate/{companyName}/{quantity}")
	public ResponseEntity<?>calculateStockPrice(@PathVariable String comapnyName,
			@PathVariable Integer quantity){
		
		ResponseEntity<?> responseEntity = null;
		Double totalPrice = null;
		System.out.println(comapnyName);
		System.out.println(quantity);
		
		try {
			
			responseEntity = stockClient.getStockPrice(comapnyName);
			System.out.println(responseEntity);
			
			int statusCode = responseEntity.getStatusCode().value();
			
			if(statusCode==200) {
				Double companyStockPrice =(Double) responseEntity.getBody();
				totalPrice=quantity * companyStockPrice;
				String response = "Total cost :: "+totalPrice;
				responseEntity = new ResponseEntity<String>(response, HttpStatus.OK);
			}
		}catch(Exception e) {
			responseEntity = new ResponseEntity<String>("company not found", HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		return responseEntity;
	}
	
	
	
	
	
}
