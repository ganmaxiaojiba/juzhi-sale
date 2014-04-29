create database juzhi_sale;

use juzhi_sale;

create table db_district (

    d_id int(11) not null auto_increment,

    dname varchar(200) not null,

    descrition varchar(500),

    primary key (d_id)

);

create table db_channel (

   c_id int(11) not null auto_increment,

   cname varchar(200) not null,

   primary key (c_id)

);

create table db_tag (

   t_id int(11) not null auto_increment,

   tname varchar(200) not null,

   description varchar(500),

   link varchar(500) not null,

   click_rate int(11),

   primary key (t_id)

);

create table db_c_t_relation (

     id int(11) not null auto_increment,

     c_id int(11) not null,

     t_id int(11) not null,
     primary key (id),

    foreign key (c_id) references db_channel(c_id) on delete cascade,

 foreign key (t_id) references db_tag(t_id) on delete cascade

);

create table db_d_c_relation (

   id int(11) not null auto_increment,

   d_id int(11) not null,

   c_id int(11) not null,
   primary key (id),

   foreign key (d_id) references db_district(d_id) on delete cascade,

   foreign key (c_id) references db_channel(c_id) on delete cascade

);