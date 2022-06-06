create table databasechangeloglock
(
    ID          int          not null
        primary key,
    LOCKED      bit          not null,
    LOCKGRANTED datetime     null,
    LOCKEDBY    varchar(255) null
);

INSERT INTO edu.databasechangeloglock (ID, LOCKED, LOCKGRANTED, LOCKEDBY) VALUES (1, false, null, null);