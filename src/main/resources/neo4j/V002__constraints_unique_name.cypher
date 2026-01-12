CREATE CONSTRAINT company_unique_name IF NOT EXISTS
FOR (c:Company) REQUIRE c.name IS UNIQUE;
