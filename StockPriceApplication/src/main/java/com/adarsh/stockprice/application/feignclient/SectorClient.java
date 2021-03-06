package com.adarsh.stockprice.application.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.adarsh.stockprice.application.dto.CompanyDto;

@FeignClient("SECTOR-SERVICE")
public interface SectorClient 
{
	@GetMapping("/sectors/{sectorName}/companies")
	public List<CompanyDto> getSectorCompanies(@PathVariable String sectorName);
}
