package com.company.collections.service;

import java.util.stream.Collectors;

import com.company.collections.response.ApiResponseDto;
import com.company.collections.utility.JobCollectionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.collections.dto.CompanyDto;
import com.company.collections.mapper.ObjectMapper;
import com.company.collections.repository.CompanyRepository;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ApiResponseDto getCompanyList() {

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatus(Boolean.TRUE);
        apiResponseDto.setData(companyRepository
                .findAllCompany()
                .stream()
                .map(e ->
                        ObjectMapper.convertEntityToDto(e, CompanyDto.class))
                .collect(Collectors
                        .toList()));
        apiResponseDto.setMsg(JobCollectionConstant.SUCCESS);

        logger.info("Successfully retrieved company list {}", apiResponseDto);
        return apiResponseDto;
    }

}

