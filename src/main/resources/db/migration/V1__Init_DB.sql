create sequence hibernate_sequence start 1 increment 1;

create table fields (
  idf int4 not null,
  active boolean not null,
  label varchar(255) not null,
  options varchar(255),
  required boolean not null,
  type varchar(255) not null,
  primary key (idf)
);

create table responses (
  idr int4 not null,
  response varchar(2048) not null,
  primary key (idr)
);

create table user_roles (
  idr int4 not null,
  idu int4, name varchar(255),
  primary key (idr)
);

create table users (
  idu int4 not null,
  activation_code varchar(255),
  email varchar(255) not null,
  enabled boolean not null,
  firstname varchar(255),
  lastname varchar(255),
  password varchar(255) not null,
  phone varchar(255),
  primary key (idu)
);