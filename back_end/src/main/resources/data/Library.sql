CREATE SCHEMA IF NOT EXISTS my_library;
USE my_library;

CREATE TABLE IF NOT EXISTS Book
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_name    VARCHAR(45) NOT NULL,
    author       VARCHAR(45) NOT NULL,
    publisher    VARCHAR(50) NULL,
    imprint_year INT         NULL,
    amount       INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS city
(
    id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    surname   VARCHAR(25) NOT NULL,
    name      VARCHAR(25) NOT NULL,
    email     VARCHAR(45) NULL,
    city_id   INT         NULL,
    street    VARCHAR(30) NULL,
    apartment VARCHAR(10) NULL,
    CONSTRAINT fk_person_city1
        FOREIGN KEY (city_id)
            REFERENCES city (id)
);

CREATE TABLE IF NOT EXISTS person_book
(
    person_id INT NOT NULL,
    book_id   INT NOT NULL,
    PRIMARY KEY (person_id, book_id),
    CONSTRAINT personbook_ibfk_1
        FOREIGN KEY (person_id)
            REFERENCES person (id),
    CONSTRAINT personbook_ibfk_2
        FOREIGN KEY (book_id)
            REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS logger
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person     VARCHAR(50) NOT NULL,
    book       VARCHAR(90) NOT NULL,
    action     VARCHAR(10) NOT NULL,
    time_stamp DATETIME    NOT NULL,
    user       VARCHAR(50) NULL
);
