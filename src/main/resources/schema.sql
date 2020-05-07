DROP TABLE IF EXISTS springbatchdb.person;

CREATE TABLE springbatchdb.person  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    email VARCHAR(100),
    age INT
);