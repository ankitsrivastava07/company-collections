package com.company.collections.service;

import com.company.collections.dto.AddCompanyDto;

import java.util.List;

import com.company.collections.dto.CompanyDto;
import com.company.collections.response.ApiResponseDto;

public interface AdminService {

    ApiResponseDto addCompany(AddCompanyDto addCompanyDto);

    ApiResponseDto deleteCompanyById(String companyId);

    List<CompanyDto> getCompanyList();
}
