package com.company.collections.controller;

import com.company.collections.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.collections.dto.AddCompanyDto;
import com.company.collections.dto.CompanyDto;
import com.company.collections.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/add-company-data")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DatabaseService databaseService;

    @PostMapping
    public ResponseEntity<?> addCompanyData(@RequestBody @Valid AddCompanyDto addCompanyDto) {
        CompanyDto companyDto = adminService.addCompany(addCompanyDto);
        return new ResponseEntity<>(companyDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCompanyList() {
        return new ResponseEntity<>(adminService.getCompanyList(),
                HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<?> getAllDB() {
        return new ResponseEntity<>(databaseService.getAllDatabases(), HttpStatus.OK);
    }

}
