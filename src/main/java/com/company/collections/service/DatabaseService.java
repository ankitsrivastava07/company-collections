package com.company.collections.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private Neo4jClient neo4jClient;

    public List<String> getAllDatabases() {
        return (List<String>) neo4jClient.query("SHOW DATABASES")
                .in("system") // important: query the system database
                .fetchAs(String.class)
                .mappedBy((typeSystem, record) -> record.get("name").asString())
                .all();
    }
}
