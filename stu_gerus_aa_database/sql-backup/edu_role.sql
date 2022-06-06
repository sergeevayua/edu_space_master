create table role
(
    id                bigint auto_increment
        primary key,
    role_name         varchar(255) null,
    modification_time datetime     null
)
    charset = utf8;

INSERT INTO edu.role (id, role_name, modification_time) VALUES (1, 'SUPERADMIN', null);
INSERT INTO edu.role (id, role_name, modification_time) VALUES (2, 'TEACHER', null);
INSERT INTO edu.role (id, role_name, modification_time) VALUES (3, 'USER', null);