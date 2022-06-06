create table chapter
(
    id                bigint auto_increment
        primary key,
    name              varchar(255) not null,
    module_id         bigint       not null,
    modification_time datetime     null
)
    charset = utf8;

