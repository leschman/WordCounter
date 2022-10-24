create schema if not exists WORD_COUNTER;

create table messages (
    id int primary key not null auto_increment,
    message varchar(255) not null,
    word_count int not null,
    message_id int not null unique
);

create unique index message_id_index on messages (message_id);