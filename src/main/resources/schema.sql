create table if not exists users (
 id int(11) not null auto_increment primary key,
 first_name varchar(64),
 last_name varchar(64),
 email varchar(64)
);