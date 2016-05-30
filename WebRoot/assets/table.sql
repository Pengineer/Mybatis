# 将建表语句保存在项目中
===========================================================================
create table t_emp(
c_id int primary key,
c_name varchar(10),
c_birth varchar(10),
c_phone varchar(30)
);

===========================================================================

create table if not exists t_student(
c_id int(5) primary key,
c_name varchar(10),
c_card_id int(5),
c_grade_id int(5),
constraint c_card_id_fk1 foreign key(c_card_id) references t_card(c_id),
constraint c_class_id_fk2 foreign key(c_grade_id) references t_grade(c_id)
);

create table if not exists t_grade(
c_id int(5) primary key,
c_name varchar(10),
c_leaderName varchar(10)
);

create table if not exists t_course(
c_id int(5) primary key,
c_name varchar(10)
);

create table if not exists t_course_student(
c_cid int(5),
c_sid int(5),
primary key(c_sid,c_cid),
constraint c_cid_id_fk2 foreign key(c_cid) references t_course(c_id),
constraint c_sid_id_fk2 foreign key(c_sid) references t_student(c_id)
);
