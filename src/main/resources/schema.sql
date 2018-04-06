DROP TABLE IF EXISTS Organization;

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR,
    full_name    VARCHAR,
    inn    VARCHAR,
    kpp    VARCHAR,
    address    VARCHAR,
    phone    VARCHAR
);