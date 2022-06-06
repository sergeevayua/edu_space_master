create table user
(
    id                bigint auto_increment
        primary key,
    first_name        varchar(255) null,
    last_name         varchar(255) null,
    phone             varchar(255) null,
    login             varchar(255) null,
    password          varchar(255) not null,
    age               int          null,
    email             varchar(255) not null,
    is_enabled        bit          null,
    modification_time datetime     null,
    constraint email
        unique (email),
    constraint login
        unique (login),
    constraint phone
        unique (phone)
)
    charset = utf8;

INSERT INTO edu.user (id, first_name, last_name, phone, login, password, age, email, is_enabled, modification_time) VALUES (1, 'Супер', 'Администратор', '89111111111', 'admin', '$2a$04$pO.jO361zgv4ig7s4jC8aOEVnpDOYShTkukPoskbY2VpCUCFR/6ui', 1, 'admin@mail.ru', true, '2021-05-20 04:14:08');
INSERT INTO edu.user (id, first_name, last_name, phone, login, password, age, email, is_enabled, modification_time) VALUES (2, null, null, null, 'teacher', '$2a$12$q91qKoHtKUMDqm3jJ7Uh0.9euuW/n8QpP.XgPAGDbAO.TTjyZS0oy', null, 'teacher@mail.ru', true, '2021-05-20 04:28:17');
INSERT INTO edu.user (id, first_name, last_name, phone, login, password, age, email, is_enabled, modification_time) VALUES (3, null, null, null, 'student', '$2a$12$jTmAmdoM6CPailDuX2zwwudnxzwVQbTTujokfr7TEne/mJapcHdr.', null, 'student@mail.ru', true, '2021-05-20 04:28:39');