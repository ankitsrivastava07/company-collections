package com.company.collections.service;

import java.util.List;
import java.util.stream.Collectors;

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
        CompanyEntity companyEntity = ObjectMapper.convertDtoToEntity(addCompanyDto, CompanyEntity.class);
        companyEntity = companyRepository.save(companyEntity);
        return ObjectMapper.convertEntityToDto(companyEntity, CompanyDto.class);
    }

    @Override
    public List<CompanyDto> getCompanyList() {
        return companyRepository
                .findAll()
                .stream()
                .map(e ->
                        ObjectMapper.convertEntityToDto(e, CompanyDto.class))
                .collect(Collectors
                        .toList());
    }

}
