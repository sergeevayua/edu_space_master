create table user_permissions
(
    id                bigint auto_increment
        primary key,
    user_id           bigint   not null,
    modification_time datetime null
);

INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (1, 1, '2021-05-20 08:38:35');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (2, 1, '2021-05-20 08:38:40');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (3, 1, '2021-05-20 08:38:54');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (4, 1, '2021-05-20 08:38:59');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (5, 1, '2021-05-20 08:39:02');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (6, 1, '2021-05-20 08:39:11');
INSERT INTO edu.user_permissions (id, user_id, modification_time) VALUES (7, 1, '2021-05-20 08:39:18');