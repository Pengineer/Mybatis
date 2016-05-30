# 将建表语句保存在项目中
===========================================================================
create table t_emp(
c_id int primary key,
c_name varchar(10),
c_birth varchar(10),
c_phone varchar(30)
);

===========================================================================

create table if not exists t_card(
c_id int(5) primary key,
c_num varchar(10)
);

create table if not exists t_student(
c_id int(5) primary key,
c_name varchar(10),
c_card_id int(5),
constraint c_card_id foreign key(c_card_id) references t_card(c_id)
);

===========================================================================