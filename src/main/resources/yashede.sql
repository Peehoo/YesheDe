drop database if exists yeshe_de;
create database yeshe_de;
use yeshe_de;

-- Original Source Table Definition
drop table if exists original_source;
CREATE TABLE original_source (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

insert into original_source (name) values ('Yeshe De');
SELECT 
    *
FROM
    original_source;


-- Recipient Type Table definition
drop table if exists recipient_type;

CREATE TABLE recipient_type (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY (description)
);

insert into recipient_type (description) values 
('Individual'), 
('Monastary'), 
('Nunnery');

SELECT 
    *
FROM
    recipient_type;

-- Recipient table definition
drop table if exists recipient;

CREATE TABLE recipient (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    country VARCHAR(255),
    state VARCHAR(255),
    city VARCHAR(255),
    street_address VARCHAR(255),
    recipientTypeId INT,
    notes VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (recipientTypeId)
        REFERENCES recipient_type (id)
);

insert into recipient (name, country, state, city, street_address, recipientTypeId, notes) values 
('Test', 'India', 'Bihar', 'Bodh Gaya', '',  1, '');

SELECT 
    *
FROM
    recipient;

-- Location table definition
-- This can be location of a source or a press plate
drop table if exists location;

CREATE TABLE location (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    UNIQUE KEY (description),
    PRIMARY KEY (id)
);

insert into location (description) values ('test press plate 1');
insert into location (description) values ('test press plate 2');

SELECT 
    *
FROM
    location;    
 
-- Source table definition
-- immediate source -> where we get it from 
drop table if exists source;

CREATE TABLE source (
    id INT NOT NULL AUTO_INCREMENT,
    originalSourceId INT,
    pages VARCHAR(255),
    bookTitle VARCHAR(255),
    sourceLocationId INT,
    immediateSource VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (sourceLocationId)
        REFERENCES location (id),
    FOREIGN KEY (originalSourceId)
        REFERENCES original_source (id)
);

insert into source (originalSourceId, pages, bookTitle, sourceLocationId, immediateSource) values 
	(1, '', 'Test title 1', 1, 'TBRC');

SELECT 
    *
FROM
    source;


-- Author table definition
drop table if exists author;

CREATE TABLE author (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    birthDate DATE,
    deathDate DATE,
    lifetime VARCHAR(255),
    biography VARCHAR(2000),
    PRIMARY KEY (id)
);

insert into author (name) values ('Test Author');

SELECT 
    *
FROM
    author;

-- Book table definition
drop table if exists book;

CREATE TABLE book (
    id INT NOT NULL AUTO_INCREMENT,
    predecessor INT,
    successor INT,
    numCopiesPrinted INT,
    pressPlateLocationId INT,
    productionYear DATE,
    title VARCHAR(255),
    notes VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (pressPlateLocationId)
        REFERENCES location (id)
);

insert into book (productionYear, title) values (20160103, 'Test Title');

SELECT 
    *
FROM
    book;

-- Category table definition
drop table if exists category;

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

insert into category (name) values ('spirituality');

SELECT 
    *
FROM
    category;
    
-- Text atble definition
drop table if exists text;

CREATE TABLE text (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(1000),
    alternateTitles VARCHAR(1000),
    authorId INT,
    PRIMARY KEY (id),
    FOREIGN KEY (authorId)
        REFERENCES author (id)
);

insert into text (title, authorId) values ('Test title 1', 1);

SELECT 
    *
FROM
    text;

-- Text_category table definition
drop table if exists text_category;

CREATE TABLE text_category (
    categoryId INT,
    textId INT,
    FOREIGN KEY (textId)
        REFERENCES text (id),
    FOREIGN KEY (categoryId)
        REFERENCES category (id)
);

insert into text_category (categoryId, textId) values (1,1);

SELECT 
    *
FROM
    text_category;

--  job table definition
drop table if exists job;

CREATE TABLE job (
    id INT NOT NULL AUTO_INCREMENT,
    textId INT,
    sourceId INT,
    PRIMARY KEY (id),
    FOREIGN KEY (textId)
        REFERENCES text (id),
    FOREIGN KEY (sourceId)
        REFERENCES source (id)
);

insert into job (textId, sourceId) values (1,1);

SELECT 
    *
FROM
    job;

-- job_file table definition
drop table if exists job_file;

CREATE TABLE job_file (
    id INT NOT NULL AUTO_INCREMENT,
    jobId INT,
    filePath VARCHAR(255),
    notes VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (jobId)
        REFERENCES job (id)
);

insert into job_file (jobId, filePath, notes) values (1, 'c:/testPath', '');

SELECT 
    *
FROM
    job_file;

-- book_file table defintion
drop table if exists book_file;

CREATE TABLE book_file (
    id INT NOT NULL AUTO_INCREMENT,
    bookId INT,
    filePath VARCHAR(255),
    notes VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (bookId)
        REFERENCES book (id)
);

insert into book_file (bookId, filePath, notes) values (1, 'C:/testPath', '');

SELECT 
    *
FROM
    book_file;

-- source_file table definition
drop table if exists source_file;

CREATE TABLE source_file (
    id INT NOT NULL AUTO_INCREMENT,
    sourceId INT,
    filePath VARCHAR(255),
    notes VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (sourceId)
        REFERENCES source (id)
);

insert into source_file (sourceId, filePath, notes) values (1, 'c:/testPath', '');

SELECT 
    *
FROM
    source_file;

-- text_book table definition
drop table if exists text_book;

CREATE TABLE text_book (
    textId INT,
    bookId INT,
    FOREIGN KEY (textId)
        REFERENCES text (id),
    FOREIGN KEY (bookId)
        REFERENCES book (id)
);

insert into text_book (textId, bookId) values (1,1);

SELECT 
    *
FROM
    text_book;
