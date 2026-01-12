package com.company.collections.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.company.collections.dto.AddCompanyDto;
import com.company.collections.dto.CompanyDto;
import com.company.collections.entity.CompanyEntity;

public class ObjectMapper {

	public static CompanyEntity convertCompanyDtoToEntity(AddCompanyDto addCompanyDto) {

		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setName(addCompanyDto.getName());
		companyEntity.setCareerURl(addCompanyDto.getCareerUrl());

		return companyEntity;
	}
	
	public static CompanyDto convertCompanyEntityToDto(CompanyEntity companyEntity) {
		
		CompanyDto dto = new CompanyDto();
		dto.setId(companyEntity.getId());
		dto.setName(companyEntity.getName());
		dto.setCareerUrl(companyEntity.getCareerURl());

		return dto;
	}

	public static List<CompanyDto> getCompanyDtoList(List<CompanyEntity> list) {
		
		return list
		.stream()
		.map(e-> convertCompanyEntityToDto(e))
		.collect(Collectors.toList());
	}
	
}
