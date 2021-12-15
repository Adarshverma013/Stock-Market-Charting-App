package com.adarsh.companyservice.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adarsh.companyservice.application.dto.IpoDto;
import com.adarsh.companyservice.application.exception.CompanyNotFoundException;
import com.adarsh.companyservice.application.exception.IpoNotFoundException;
import com.adarsh.companyservice.application.service.IpoService;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/ipos")
public class IpoController 
{
	@Autowired
	private IpoService ipoService;

	@CrossOrigin(origins= "*")
	@GetMapping(path = "")
	public ResponseEntity<List<IpoDto>> findAll() {
		return ResponseEntity.ok(ipoService.findAll());
	}

	@CrossOrigin(origins= "*")
	@GetMapping(path = "/{id}")
	public ResponseEntity<IpoDto> findById(@PathVariable String id)throws IpoNotFoundException
	{
		IpoDto ipoDto = ipoService.findById(id);
		if(ipoDto == null) {
			throw new IpoNotFoundException("Ipo not found for id : " + id);
		}
		return ResponseEntity.ok(ipoDto);
	}

	@CrossOrigin(origins= "*")
	@PostMapping(path = "")
	public ResponseEntity<IpoDto> save(@RequestBody IpoDto ipoDto)throws CompanyNotFoundException
	{
		IpoDto addedIpoDto = ipoService.save(ipoDto);
		if(addedIpoDto == null) {
			throw new CompanyNotFoundException("Company not found with name : " + ipoDto.getCompanyName());
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(addedIpoDto);
	}

	@CrossOrigin(origins= "*")
	@PutMapping(path = "")
	public ResponseEntity<IpoDto> update(@RequestBody IpoDto ipoDto)
			throws IpoNotFoundException
	{
		IpoDto updatedIpoDto = ipoService.update(ipoDto);
		if(updatedIpoDto == null) {
			throw new IpoNotFoundException("Ipo not found for id : " + ipoDto.getId());
		}
		return ResponseEntity.ok(updatedIpoDto);
	}

	@CrossOrigin(origins= "*")
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable String id) {
		ipoService.deleteById(id);
	}
}
