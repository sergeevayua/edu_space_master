create table course
(
    id                bigint auto_increment
        primary key,
    name              varchar(255)     not null,
    course_group_id   bigint           not null,
    is_allow_to_view  bit default b'0' null,
    modification_time datetime         null
)
    charset = utf8;

INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (1, 'Java', 1, false, '2021-05-20 08:38:35');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (2, 'Python', 1, false, '2021-05-20 08:38:40');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (3, 'Angular', 2, false, '2021-05-20 08:38:54');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (4, 'React', 2, false, '2021-05-20 08:38:59');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (5, 'Vue', 2, false, '2021-05-20 08:39:02');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (6, 'Docker', 3, false, '2021-05-20 08:39:10');
INSERT INTO edu.course (id, name, course_group_id, is_allow_to_view, modification_time) VALUES (7, 'Kubernetes', 3, false, '2021-05-20 08:39:17');