package com.company.collections.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.collections.service.UserService;

@RestController
@RequestMapping("/api/v1/company")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getCompanyList() {
        return new ResponseEntity<>(userService.getCompanyList(),
                HttpStatus.OK);
    }
}
