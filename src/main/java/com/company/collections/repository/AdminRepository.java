package com.company.collections.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.company.collections.entity.CompanyEntity;

public interface AdminRepository extends Neo4jRepository<CompanyEntity, String>{

}
