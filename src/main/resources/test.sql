

drop table if exists orginfo CASCADE


drop table if exists srsvc CASCADE


drop table if exists test0 CASCADE


drop table if exists test1 CASCADE


drop table if exists todo CASCADE


drop table if exists user_entity CASCADE


create table orginfo (
                         id varchar(255) not null,
                         orgcd varchar(255) not null,
                         orgnm varchar(255) not null,
                         primary key (id)
)

create table srsvc (
                       srid varchar(255) not null,
                       caller varchar(255),
                       consday varchar(255),
                       consdetail clob,
                       mgr varchar(255),
                       orgcd varchar(255),
                       orgnm varchar(255),
                       proc varchar(255),
                       procday varchar(255),
                       procdetail clob,
                       receiver varchar(255),
                       userid varchar(255),
                       primary key (srid)
)

create table test0 (
                       id varchar(255) not null,
                       data0 varchar(255),
                       data1 varchar(255),
                       data1_1 varchar(255),
                       primary key (id)
)

create table test1 (
                       id varchar(255) not null,
                       data2 varchar(255),
                       data3 varchar(255),
                       data3_3 varchar(255),
                       primary key (id)
)

create table todo (
                      id varchar(255) not null,
                      done boolean not null,
                      title varchar(255),
                      user_id varchar(255),
                      primary key (id)
)

create table user_entity (
                             id varchar(255) not null,
                             auth_provider varchar(255),
                             password varchar(255),
                             role varchar(255),
                             username varchar(255) not null,
                             primary key (id)
)
