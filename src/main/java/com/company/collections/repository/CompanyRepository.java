package com.company.collections.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.company.collections.entity.CompanyEntity;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CompanyRepository extends Neo4jRepository<CompanyEntity, String> {

    List<CompanyEntity> findByName(String name);

    @Query("MATCH (n:Company) " +
            "RETURN n ORDER BY n.createdDate")
    List<CompanyEntity> findAllCompany();

    @Query("MATCH (c:Company) WHERE elementId(c) = '?1' DETACH DELETE c")
    void deleteById(Long companyId);
}
