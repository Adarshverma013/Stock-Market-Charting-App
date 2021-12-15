package com.adarsh.sectorservice.application.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adarsh.sectorservice.application.dto.CompanyDto;
import com.adarsh.sectorservice.application.dto.SectorDto;
import com.adarsh.sectorservice.application.exception.SectorNotFoundException;
import com.adarsh.sectorservice.application.service.SectorService;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/sectors")
@Slf4j
public class SectorController 
{
	@Autowired
	private SectorService sectorService;

	@CrossOrigin(origins= "*")
	@GetMapping(path = "")
	public ResponseEntity<List<SectorDto>> findAll() {
		return ResponseEntity.ok(sectorService.findAll());
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}")
	public ResponseEntity<SectorDto> findById(@PathVariable String id)
			throws SectorNotFoundException 
	{
		SectorDto sectorDto = sectorService.findById(id);
		if(sectorDto == null) {
			throw new SectorNotFoundException("Sector not found for id : " + id);
		}
		return ResponseEntity.ok(sectorDto);
	}

	@CrossOrigin(origins= "*")
	@PostMapping(path = "")
	public ResponseEntity<SectorDto> save(@RequestBody SectorDto sectorDto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(sectorService.save(sectorDto));
	}

	@CrossOrigin(origins= "*")
	@PutMapping(path = "")
	public ResponseEntity<SectorDto> update(@RequestBody SectorDto sectorDto)
			throws SectorNotFoundException
	{
		SectorDto updatedSectorDto = sectorService.save(sectorDto);
		if(updatedSectorDto == null) {
			throw new SectorNotFoundException("Sector not found for id : " + sectorDto.getId());
		}
		return ResponseEntity.ok(updatedSectorDto);
	}

	@CrossOrigin(origins= "*")
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable String id) {
		sectorService.deleteById(id);
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String id)
			throws SectorNotFoundException 
	{
		List<CompanyDto> companyDtos = sectorService.getCompanies(id);
		if(companyDtos == null) {
			throw new SectorNotFoundException("Sector not found for id : " + id);
		}
		return ResponseEntity.ok(companyDtos);
	}
	
	/* Feign Client Mapping */
	@CrossOrigin(origins= "*")
	@PostMapping(path = "/{sectorName}/companies")
	public void addCompanyToSector(@PathVariable String sectorName, @RequestBody CompanyDto companyDto)
			throws SectorNotFoundException 
	{
		log.info("Into add company of sector controller");
		SectorDto sectorDto = sectorService.addCompanyToSector(sectorName, companyDto);
		if(sectorDto == null) {
			throw new SectorNotFoundException("Sector not found with name : " + sectorName);
		}
	}
}
