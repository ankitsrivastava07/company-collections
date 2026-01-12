package com.company.collections.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.company.collections.entity.CompanyEntity;

import java.util.List;

public interface CompanyRepository extends Neo4jRepository<CompanyEntity, String>{

    List<CompanyEntity> findByName(String name);
}
