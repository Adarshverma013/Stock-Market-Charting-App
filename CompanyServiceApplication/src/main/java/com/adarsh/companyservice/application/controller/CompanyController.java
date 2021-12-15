package com.adarsh.companyservice.application.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.companyservice.application.dto.CompanyDto;
import com.adarsh.companyservice.application.dto.IpoDto;
import com.adarsh.companyservice.application.dto.StockPriceDto;
import com.adarsh.companyservice.application.exception.CompanyNotFoundException;
import com.adarsh.companyservice.application.service.CompanyService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/companies")
@Slf4j
public class CompanyController 
{
	@Autowired
	private CompanyService companyService;

	@CrossOrigin(origins= "*")
	@GetMapping(path = "")
	public ResponseEntity<List<CompanyDto>> getCompanies() 
	{
		return ResponseEntity
				.ok(companyService.getCompanies());
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}")
	public ResponseEntity<CompanyDto> getCompanyDetails(@PathVariable String id)
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.findById(id);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(companyDto);
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/match/{pattern}")
	public ResponseEntity<List<CompanyDto>> getMatchingCompanies(@PathVariable String pattern) 
	{
		return ResponseEntity.ok(companyService.getMatchingCompanies(pattern));
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}/ipos")
	public ResponseEntity<List<IpoDto>> getCompanyIpoDetails(@PathVariable String id)
			throws CompanyNotFoundException 
	{
		List<IpoDto> ipoDtos = companyService.getCompanyIpoDetails(id);
		if(ipoDtos == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(ipoDtos);
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}/stockPrices")
	public ResponseEntity<List<StockPriceDto>> getStockPrices(@PathVariable String id)
			throws CompanyNotFoundException
	{
		List<StockPriceDto> stockPriceDtos = companyService.getStockPrices(id);
		if(stockPriceDtos == null) {
			throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(stockPriceDtos);
	}

	@CrossOrigin(origins= "*")
	@PostMapping(path = "")
	@HystrixCommand(fallbackMethod = "defaultResponse")
	public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
		log.info("Into company controller add company");
		return ResponseEntity.ok(companyService.addCompany(companyDto));
	}

	@CrossOrigin(origins= "*")
	@PutMapping(path = "")
	public ResponseEntity<CompanyDto> editCompany(@RequestBody CompanyDto companyDto)
			throws CompanyNotFoundException 
	{
		CompanyDto updatedCompanyDto = companyService.editCompany(companyDto);
		if(updatedCompanyDto == null) {
			throw new CompanyNotFoundException("Company not found at id : " + companyDto.getId());
		}
		return ResponseEntity.ok(updatedCompanyDto);
	}

	@CrossOrigin(origins= "*")
	@DeleteMapping(path = "/{id}")
	public void deleteCompany(@PathVariable String id) {
		companyService.deleteCompany(id);
	}
	
	/* Feign Client Mappings */

	@CrossOrigin(origins= "*")
	@PostMapping(path = "/{companyName}/ipos")
	public void addIpoToCompany(@PathVariable String companyName, @RequestBody IpoDto ipoDto)
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addIpoToCompany(companyName, ipoDto);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not with name : " + companyName);
		}
	}

	@CrossOrigin(origins= "*")
	@PostMapping(path = "/{companyCode}/stockPrices")
	public void addStockPriceToCompany(@PathVariable String companyCode, @RequestBody StockPriceDto stockPriceDto) 
			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addStockPriceToCompany(companyCode, stockPriceDto);
		if(companyDto == null) {
			throw new CompanyNotFoundException("Company not with code : " + companyCode);
		}
	}
	
	/* Feign Client Default Response */
	@CrossOrigin(origins= "*")
	public ResponseEntity<?> defaultResponse(@RequestBody CompanyDto companyDto) {
		String err = "Fallback error as the microservice is down.";
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(err);
	}
}
