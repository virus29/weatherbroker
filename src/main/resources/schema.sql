DROP TABLE IF EXISTS Organization;

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR(255),
    fullname    VARCHAR(255),
    inn    VARCHAR(255),
    kpp    VARCHAR(255),
    address    VARCHAR(255),
    phone    VARCHAR(255)
);