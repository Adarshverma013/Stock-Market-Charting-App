package com.adarsh.stockexchange.application.controller;

import java.util.List;

import com.adarsh.stockexchange.application.dto.CompanyDto;
import com.adarsh.stockexchange.application.dto.StockExchangeDto;
import com.adarsh.stockexchange.application.exception.StockExchangeNotFoundException;
import com.adarsh.stockexchange.application.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/stockExchanges")
public class StockExchangeController 
{
	@Autowired
	private StockExchangeService stockExchangeService;

	@CrossOrigin(origins= "*")
	@GetMapping(path = "")
	public ResponseEntity<List<StockExchangeDto>> getStockExchangesList() {
		return ResponseEntity.ok(stockExchangeService.getStockExchangesList());
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockExchangeDto> getStockExchangeDetails(@PathVariable String id)
			throws StockExchangeNotFoundException
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.findById(id);
		if(stockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(stockExchangeDto);
	}

	@CrossOrigin(origins= "*")
	@PostMapping(path = "")
	public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchangeDto stockExchangeDto) {
		return ResponseEntity.ok(stockExchangeService.addStockExchange(stockExchangeDto));
	}

	@CrossOrigin(origins= "*")
	@PutMapping(path = "")
	public ResponseEntity<StockExchangeDto> editStockExchange(@RequestBody StockExchangeDto stockExchangeDto)
			throws StockExchangeNotFoundException 
	{
		StockExchangeDto updatedStockExchangeDto = stockExchangeService.editStockExchange(stockExchangeDto);
		if(updatedStockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + stockExchangeDto.getId());
		}
		return ResponseEntity.ok(updatedStockExchangeDto);
	}

	@CrossOrigin(origins= "*")
	@DeleteMapping(path = "/{id}")
	public void deleteStockExchange(@PathVariable String id) {
		stockExchangeService.deleteStockExchange(id);
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String id)
			throws StockExchangeNotFoundException  
	{
		List<CompanyDto> companyDtos = stockExchangeService.getCompanies(id);
		if(companyDtos == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(companyDtos);
	}
	
	/* Feign Client Mapping */

	@CrossOrigin(origins= "*")
	@PostMapping(path = "/{stockExchangeName}/companies")
	public void addCompanyToStockExchange(@PathVariable String stockExchangeName, @RequestBody CompanyDto companyDto)
			throws StockExchangeNotFoundException  
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.addCompanyToStockExchange(stockExchangeName, companyDto);
		if(stockExchangeDto == null) {
			throw new StockExchangeNotFoundException("Stock Exchange Not Found with name : " + stockExchangeName);
		}
	}
}
