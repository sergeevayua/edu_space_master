create table databasechangelog
(
    ID            varchar(255) not null,
    AUTHOR        varchar(255) not null,
    FILENAME      varchar(255) not null,
    DATEEXECUTED  datetime     not null,
    ORDEREXECUTED int          not null,
    EXECTYPE      varchar(10)  not null,
    MD5SUM        varchar(35)  null,
    DESCRIPTION   varchar(255) null,
    COMMENTS      varchar(255) null,
    TAG           varchar(255) null,
    LIQUIBASE     varchar(20)  null,
    CONTEXTS      varchar(255) null,
    LABELS        varchar(255) null,
    DEPLOYMENT_ID varchar(10)  null
);

INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('1', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:05', 1, 'EXECUTED', '8:01154392e11d943f4a7866fbaa2b520f', 'createTable tableName=user', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('2', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:06', 2, 'EXECUTED', '8:e4c0a183c15459e0125b5cd19f9b0a13', 'createTable tableName=role', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('3', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:07', 3, 'EXECUTED', '8:6537a88e88fcfbb5e1f26e7546ff8d4b', 'insert tableName=role; insert tableName=role; insert tableName=role', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('4', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:08', 4, 'EXECUTED', '8:ff1c68aa0b29527677662239d6090503', 'insert tableName=user', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('5', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:09', 5, 'EXECUTED', '8:a3570362307caa48f8eaf00e79436551', 'createTable tableName=permissions', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('6', 'agerus', 'db/changelog/2020-02-29/create-user-and-rule.xml', '2021-05-20 04:14:10', 6, 'EXECUTED', '8:7b34b98e8b5a0824231621e572c693db', 'insert tableName=permissions; insert tableName=permissions; insert tableName=permissions', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('1', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:11', 7, 'EXECUTED', '8:c8105cd622a8a959689b341706a84cf1', 'createTable tableName=course_group', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('2', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:12', 8, 'EXECUTED', '8:2738a086cdcc49f309b700d17c5ab0ef', 'createTable tableName=course', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('3', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:13', 9, 'EXECUTED', '8:f698effac3ee9ccf0ba3c80e5711dd61', 'createTable tableName=module', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('4', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:14', 10, 'EXECUTED', '8:d199202e0bf6ce547b517adeaaecf24e', 'createTable tableName=chapter', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('5', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:16', 11, 'EXECUTED', '8:d58fa77956f1937b972ab35a2b538d77', 'createTable tableName=exercise', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('6', 'agerus', 'db/changelog/2020-06-25/course-api.xml', '2021-05-20 04:14:17', 12, 'EXECUTED', '8:2b43d13d1de6b12b3f91525541748c2f', 'createTable tableName=token', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('2', 'agerus', 'db/changelog/2020-06-14/rename-column-in-role-table.xml', '2021-05-20 04:14:18', 13, 'EXECUTED', '8:04dff2f44b4c574bbbc279bc0456f5b5', 'createTable tableName=professional', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('3', 'agerus', 'db/changelog/2020-06-14/rename-column-in-role-table.xml', '2021-05-20 04:14:19', 14, 'EXECUTED', '8:e8baeb70a51571943ca53b4e83173c8a', 'insert tableName=professional', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('4', 'agerus', 'db/changelog/2020-06-14/rename-column-in-role-table.xml', '2021-05-20 04:14:19', 15, 'EXECUTED', '8:15ffc48ac88424a3c9205cf75ad1d219', 'insert tableName=professional', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('1', 'agerus', 'db/changelog/2021-05-13/add-link-between-user-and-course.xml', '2021-05-20 04:14:21', 16, 'EXECUTED', '8:9620cef5f4c4b04ec9990399b8473a94', 'createTable tableName=user_permissions', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('2', 'agerus', 'db/changelog/2021-05-13/add-link-between-user-and-course.xml', '2021-05-20 04:14:22', 17, 'EXECUTED', '8:1db8ef9400a2db9d0c91b851651dfa51', 'createTable tableName=user_and_course_permissions', '', null, '3.6.3', null, null, '1484044379');
INSERT INTO edu.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID) VALUES ('3', 'agerus', 'db/changelog/2021-05-13/add-link-between-user-and-course.xml', '2021-05-20 04:14:24', 18, 'EXECUTED', '8:2eaea010dcdc6f5d2a8818a083a262f4', 'createTable tableName=course_permissions', '', null, '3.6.3', null, null, '1484044379');