create table token
(
    id      bigint auto_increment
        primary key,
    token   varchar(512) null,
    user_id bigint       null
)
    charset = utf8;

