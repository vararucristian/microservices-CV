create database
if not exists employees;

use employees;

drop table if exists employees;

create table employees
(
    CNP varchar(15) not null unique,
    firstName varchar(25) not null,
    lastName varchar(50) not null,
    gendre varchar(10) not null,
    adress varchar(100),
    ID int primary key,
    salary int not null,
    contractCode int unique not null,
    position varchar(50) not null
);

describe employees;

drop table if exists supervision;

create table supervision
(
    id_superviser int not null ,
    id_supervised int not null ,
    foreign key (id_superviser) references employees(ID),
    foreign key (id_supervised) references employees(ID),
    UNIQUE(id_supervised,id_superviser)
);

describe supervision;

drop table if exists cv;

create table cv
(
    id int primary key,
    CNP varchar(15) not null unique,
    firstName varchar(25) not null,
    lastName varchar(50) not null,
    gendre varchar(10) not null,
    adress varchar(100),
    position varchar(50) not null,
    desciption varchar(300) not null,
    jobRelatedSkils varchar(100) not null
);

describe cv;

drop TABLE if EXISTS workExperience;
create TABLE workExperience
(
    id int PRIMARY key,
    years int not null,
    jobtitle varchar(50) not null,
    company VARCHAR(50) not null
);
describe workExperience;

drop table if exists have;
create table have
(
    id_cv int not null ,
    id_workExperience int not null ,
    foreign key (id_cv) references cv(ID),
    foreign key (id_workExperience) references workExperience(ID),
    UNIQUE(id_cv,id_workExperience)
);

describe have;