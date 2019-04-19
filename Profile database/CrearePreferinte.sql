create table preferences (
    id_task int(11) NOT NULL ,
	acc_id int(11) NOT NULL ,
	8_10 varchar(20) NOT NULL ,
    10_12 varchar(20) NOT NULL ,
    12_14 varchar(20) NOT NULL ,
    14_16 varchar(20) NOT NULL ,
    16_18 varchar(20) NOT NULL ,
    18_20 varchar(20) NOT NULL ,
    primary key (id_task) ,
    foreign key (acc_id) references profile(account_id)
	);
