package com.company.collections.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class SubsidiaryCompanyEntity {
    @Id
    @GeneratedValue
    private String id;
    @Property("name")
    private String name;
    private List<CompanyEntity> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompanyEntity> getList() {
        return list;
    }

    public void setList(List<CompanyEntity> list) {
        this.list = list;
    }

}
