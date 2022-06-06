create table permissions
(
    user_id bigint null,
    role_id bigint null
)
    charset = utf8;

INSERT INTO edu.permissions (user_id, role_id) VALUES (1, 1);
INSERT INTO edu.permissions (user_id, role_id) VALUES (1, 2);
INSERT INTO edu.permissions (user_id, role_id) VALUES (1, 3);
INSERT INTO edu.permissions (user_id, role_id) VALUES (2, 3);
INSERT INTO edu.permissions (user_id, role_id) VALUES (3, 3);
INSERT INTO edu.permissions (user_id, role_id) VALUES (2, 2);