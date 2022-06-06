create table course_permissions
(
    user_permissions_id bigint       null,
    permission          varchar(255) null
)
    charset = utf8;

INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (1, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (1, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (1, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (2, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (2, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (2, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (3, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (3, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (3, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (4, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (4, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (4, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (5, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (5, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (5, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (6, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (6, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (6, 'USER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (7, 'SUPERADMIN');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (7, 'TEACHER');
INSERT INTO edu.course_permissions (user_permissions_id, permission) VALUES (7, 'USER');