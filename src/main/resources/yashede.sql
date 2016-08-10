drop database if exists yeshe_de;
create database yeshe_de;
use yeshe_de;

show tables;

create table original_source 
(
id int PRIMARY KEY,
name varchar(255)
);

insert into original_source values  (1, 'Yeshe De');
select * from original_source;


create table recipient_location
(
id int primary key,
country varchar(255),
state varchar(255),
city varchar(255),
street_address varchar(255)
);

insert into recipient_location values (1, 'India', 'Bihar', 'Bodh Gaya', '');

drop table if exists recipient_type;

create table recipient_type
(
id int primary key,
description varchar(255)
);

insert into recipient_type (id, description) values 
(0, 'Individual'), 
(1, 'Monastary'), 
(2, 'Nunnery')
;

create table recipient_name
(
id int primary key,
recipientTypeId int,
name varchar(255),
FOREIGN KEY (recipientTypeId) REFERENCES recipient_type(id)
);

insert into recipient_name (id, recipientTypeId, name) values 
(0, 0, 'Monk'), 
(1, 1, 'Kathog'), 
(2, 2, 'Yachen Gar')
;

select * from recipient_name, recipient_type where recipient_name.recipientTypeId = recipient_type.id and recipient_type.id = 1;

create table recipient
(
id int primary key,
name varchar(255),
recipientLocationId int,
recipientTypeId int,
notes varchar(255),
foreign key (recipientLocationId) references recipient_location(id),
foreign key (recipientTypeId) references recipient_type(id)
);

create table location /* This can be location of a source or a press plate*/
(
id int primary key,
description varchar(255)    
);

create table source
(
id int primary key,
originalSourceId int,
pages varchar(255),
bookTitle varchar(255),
sourceLocationId int,
immediateSource varchar(255), /* where we got it from */
foreign key (sourceLocationId) references location(id),
foreign key (originalSourceId) references original_source(id)
);

create table author
(
id int primary key,
name varchar(255),
birthDate date,
deathDate date,
lifetime varchar(255),
biography varchar(2000)
);

create table book
(
id int primary key,
predecessor int,
successor int,
numCopiesPrinted int,
pressPlateLocationId int,
productionYear date,
title varchar(255),
notes varchar(1000),
foreign key (pressPlateLocationId) references location(id)
);

create table category
(
id int primary key,
name varchar(255)
);

create table text
(
id int primary key,
title varchar(255),
alternateTitles varchar(1000),
authorId int,
foreign key (authorId) references author(id)
);

create table text_category
(
categoryId int,
textId int,
foreign key (textId) references text(id),
foreign key (categoryId) references category(id)
);


create table job
(
id int primary key,
textId int,
sourceId int,
foreign key (textId) references text(id),
foreign key (sourceId) references source(id) 
);

create table job_file
(
id int primary key,
jobId int,
filePath varchar(255),
notes varchar(1000),
foreign key (jobId) references job(id)
);

create table book_file
(
id int primary key,
bookId int,
filePath varchar(255),
notes varchar(1000),
foreign key (bookId) references book(id)
);

create table source_file
(
id int primary key,
sourceId int,
filePath varchar(255),
notes varchar(1000),
foreign key (sourceId) references source(id)
);

create table text_book
(
textId int,
bookId int,
foreign key (textId) references text(id),
foreign key (bookId) references book(id)
);



