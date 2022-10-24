create schema if not exists WORD_COUNTER;

create table MESSAGES (
    ID int not null,
    MESSAGE varchar(100) not null,
    WORD_COUNT int not null
);