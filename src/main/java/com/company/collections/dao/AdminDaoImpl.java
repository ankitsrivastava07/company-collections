package com.company.collections.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.collections.entity.CompanyEntity;
import com.company.collections.repository.CompanyRepository;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyEntity addCompanyData(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }

    @Override
    public void deleteCompanyById(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
