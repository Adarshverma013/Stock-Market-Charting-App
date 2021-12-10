package com.adarsh.companyservice.application.dao;

import java.util.List;
import java.util.Optional;

import com.adarsh.companyservice.application.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String>
{
	public Optional<Company> findById(String id);
	public List<Company> findByNameIgnoreCaseContaining(String pattern);
	public Company findByName(String name);
	public Company findByCode(String code);
}
