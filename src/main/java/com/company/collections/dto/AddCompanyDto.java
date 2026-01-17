package com.company.collections.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class AddCompanyDto {

    @NotNull(message = "Company name can't be empty")
    private String name;
    private String jobUrlById;
    @NotNull(message = "Career url can't be empty")
    private String careerUrl;
    private String createdBy;


}
