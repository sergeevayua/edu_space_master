create table professional
(
    id                bigint auto_increment
        primary key,
    name              varchar(255) not null,
    link              varchar(255) not null,
    icon              varchar(255) not null,
    modification_time datetime     null
)
    charset = utf8;

INSERT INTO edu.professional (id, name, link, icon, modification_time) VALUES (1, 'IT_JOBS', 'it-jobs', 'desktop_mac', '2021-05-20 04:14:18');
INSERT INTO edu.professional (id, name, link, icon, modification_time) VALUES (2, 'SCHOOL_EXAMS', 'school-exams', 'desktop_mac', '2021-05-20 04:14:19');