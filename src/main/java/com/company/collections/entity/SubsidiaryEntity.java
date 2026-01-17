package com.company.collections.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
@Getter
@Setter
public class SubsidiaryEntity {

    @Id
    @GeneratedValue
    private String id;
    @Property("name")
    private String name;
    private List<CompanyEntity> list;

}
