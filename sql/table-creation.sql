create table Products (
id bigint not null auto_increment,
name varchar(120) not null,
price decimal (12, 2) not null,
description varchar(300) null,
category varchar (120) not null,
discount decimal (4, 2) null,
discountPrice decimal (12,2) null,
created timestamp default current_timestamp,
primary key (id));