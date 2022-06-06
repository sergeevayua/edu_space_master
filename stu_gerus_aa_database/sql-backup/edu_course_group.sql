create table course_group
(
    id                bigint auto_increment
        primary key,
    name              varchar(255) not null,
    professional_id   bigint       not null,
    modification_time datetime     null
)
    charset = utf8;

INSERT INTO edu.course_group (id, name, professional_id, modification_time) VALUES (1, 'Backend', 1, '2021-05-20 08:38:11');
INSERT INTO edu.course_group (id, name, professional_id, modification_time) VALUES (2, 'Frontend', 1, '2021-05-20 08:38:16');
INSERT INTO edu.course_group (id, name, professional_id, modification_time) VALUES (3, 'DevOps', 1, '2021-05-20 08:38:21');