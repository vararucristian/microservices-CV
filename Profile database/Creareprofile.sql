create database if not exists profile;

use profile;

drop table if exists profile;

create table profile (
	name varchar(100) NOT NULL ,
	last_name varchar(45) NOT NULL,
	email varchar(45) NOT NULL,
	adress varchar(45) NOT NULL,
	username varchar(45) NOT NULL,
	position_in_company varchar(45) NOT NULL,
	account_id int primary key,
	age int NOT NULL,
	gender varchar(45) NOT NULL
	id_preferinta int NOT NULL,
	);

describe profile;
