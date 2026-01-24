package com.company.collections.dto;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;

@Validated
public class AddCompanyDto {

    @NotNull(message = "Company name can't be empty")
    private String name;
    private String jobUrlById;
    @NotNull(message = "Career url can't be empty")
    private String careerUrl;
    private String createdBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobUrlById() {
        return jobUrlById;
    }

    public void setJobUrlById(String jobUrlById) {
        this.jobUrlById = jobUrlById;
    }

    public String getCareerUrl() {
        return careerUrl;
    }

    public void setCareerUrl(String careerUrl) {
        this.careerUrl = careerUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
