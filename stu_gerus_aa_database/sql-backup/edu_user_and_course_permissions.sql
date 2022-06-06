create table user_and_course_permissions
(
    course_id           bigint null,
    user_permissions_id bigint null
)
    charset = utf8;

INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (1, 1);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (2, 2);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (3, 3);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (4, 4);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (5, 5);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (6, 6);
INSERT INTO edu.user_and_course_permissions (course_id, user_permissions_id) VALUES (7, 7);