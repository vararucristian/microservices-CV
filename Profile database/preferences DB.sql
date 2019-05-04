create database if not exists preferences;

use preferences;

drop table if exists preferences;

create table preferences (
	morning varchar(45) NOT NULL ,
	afternoon varchar(45) NOT NULL,
	evening varchar(45) NOT NULL,
	id_user int primary key
	);

insert into preferences values ('relaxed','tired','focused',101);
insert into preferences values ('focused','tired','relaxed',102);
insert into preferences values ('tired','tired','focused',103);
insert into preferences values ('relaxed','relaxed','focused',104);