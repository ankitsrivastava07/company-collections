package com.company.collections.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.collections.dto.AddCompanyDto;
import com.company.collections.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin/add-company-data")
public class AdminController {
	
	private AdminService adminService;

	@PostMapping
	public ResponseEntity<?> addCompanyData(AddCompanyDto addCompanyDto) {
		adminService.addCompany(addCompanyDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
