package com.adarsh.companyservice.application.service;

import java.util.List;

import com.adarsh.companyservice.application.dto.IpoDto;

public interface IpoService 
{
	public List<IpoDto> findAll();
	public IpoDto findById(String id);
	public IpoDto save(IpoDto ipoDto);
	public IpoDto update(IpoDto ipoDto);
	public void deleteById(String id);
}
