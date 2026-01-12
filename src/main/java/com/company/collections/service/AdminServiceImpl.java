package com.company.collections.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.collections.dto.AddCompanyDto;
import com.company.collections.dto.CompanyDto;
import com.company.collections.entity.CompanyEntity;
import com.company.collections.mapper.ObjectMapper;
import com.company.collections.repository.CompanyRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public CompanyDto addCompany(AddCompanyDto addCompanyDto) {

		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setName(addCompanyDto.getName());
		companyEntity.setCareerURl(addCompanyDto.getCareerUrl());

		companyEntity = companyRepository.save(companyEntity);
		CompanyDto companyDto = new CompanyDto();
		companyDto.setCareerUrl(companyEntity.getCareerURl());
		companyDto.setId(companyEntity.getId());
		companyDto.setName(addCompanyDto.getName());

		return companyDto;
	}

	@Override
	public List<CompanyDto> getCompanyList() {
		return ObjectMapper
				.getCompanyDtoList(companyRepository
						.findAll());
	}

}
