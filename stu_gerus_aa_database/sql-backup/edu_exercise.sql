create table exercise
(
    id                bigint auto_increment
        primary key,
    chapter_id        bigint       not null,
    description       varchar(255) not null,
    numeration        bigint       not null,
    content           varchar(255) null,
    modification_time datetime     null
)
    charset = utf8;

