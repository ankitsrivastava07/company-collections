package com.company.collections.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.company.collections.dto.CompanyDto;
import com.company.collections.mapper.ObjectMapper;
import com.company.collections.repository.CompanyRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getCompanyList() {
        return companyRepository    
                .findAllCompany(Sort.by(Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(e ->
                        ObjectMapper.convertEntityToDto(e, CompanyDto.class))
                .collect(Collectors
                        .toList());
    }

}

