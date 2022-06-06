#getAllChaptersByModule
select
    chapter0_.id as id1_0_,
    chapter0_.modification_time as modifica2_0_,
    chapter0_.module_id as module_i4_0_,
    chapter0_.name as name3_0_
from
    chapter chapter0_
where
        chapter0_.module_id=?;

# getAllCoursesByCourseGroupId
select
    course0_.id as id1_1_,
    course0_.modification_time as modifica2_1_,
    course0_.is_allow_to_view as is_allow3_1_,
    course0_.course_group_id as course_g5_1_,
    course0_.name as name4_1_
from
    course course0_
where
        course0_.course_group_id=?;

# getAllCourseGroupsByProfessionalId
select
    coursegrou0_.id as id1_2_,
    coursegrou0_.modification_time as modifica2_2_,
    coursegrou0_.name as name3_2_,
    coursegrou0_.professional_id as professi4_2_
from
    course_group coursegrou0_
where
        coursegrou0_.professional_id=?;

# getAggregatedChartData
select
    course_group.name,
    count(c.id)
from
    course_group join course c on course_group.id = c.course_group_id
group by course_group_id;

# getExerciseByChapterAndNumeration
select
    exercise0_.id as id1_4_,
    exercise0_.modification_time as modifica2_4_,
    exercise0_.chapter_id as chapter_6_4_,
    exercise0_.content as content3_4_,
    exercise0_.description as descript4_4_,
    exercise0_.numeration as numerati5_4_
from
    exercise exercise0_
        inner join
    chapter chapter1_
where
        chapter1_.id=?
  and exercise0_.numeration=?;

# getAllExercisesByChapterId
select
    exercise0_.id as id1_4_,
    exercise0_.modification_time as modifica2_4_,
    exercise0_.chapter_id as chapter_6_4_,
    exercise0_.content as content3_4_,
    exercise0_.description as descript4_4_,
    exercise0_.numeration as numerati5_4_
from
    exercise exercise0_
where
        exercise0_.chapter_id=?;

# getAllModulesByCourse
select
    module0_.id as id1_5_,
    module0_.modification_time as modifica2_5_,
    module0_.course_id as course_i4_5_,
    module0_.name as name3_5_
from
    module module0_
where
        module0_.course_id=?;

# getUserPermissionByUserId
select
    userpermis0_.id as id1_12_,
    userpermis0_.modification_time as modifica2_12_,
    userpermis0_.user_id as user_id3_12_
from
    user_permissions userpermis0_
        left outer join
    user user1_
    on userpermis0_.user_id=user1_.id
where
        user1_.id=?;

# getAllByUserId
select
    userpermis0_.id as id1_12_,
    userpermis0_.modification_time as modifica2_12_,
    userpermis0_.user_id as user_id3_12_
from
    user_permissions userpermis0_
        left outer join
    user user1_
    on userpermis0_.user_id=user1_.id
        left outer join
    user_and_course_permissions course2_
    on userpermis0_.id=course2_.course_id
        left outer join
    course course3_
    on course2_.user_permissions_id=course3_.id
where
        user1_.id=?;

# getRoleByIds
select
    role0_.id as id1_8_,
    role0_.modification_time as modifica2_8_,
    role0_.role_name as role_nam3_8_
from
    role role0_
where
        role0_.id in (
        ?
        );

# getUserByEmailOrPhoneOrLogin
select
    user0_.id as id1_10_,
    user0_.modification_time as modifica2_10_,
    user0_.age as age3_10_,
    user0_.email as email4_10_,
    user0_.is_enabled as is_enabl5_10_,
    user0_.first_name as first_na6_10_,
    user0_.last_name as last_nam7_10_,
    user0_.login as login8_10_,
    user0_.password as password9_10_,
    user0_.phone as phone10_10_
from
    user user0_
where
        user0_.login=?
   or user0_.email=?
   or user0_.phone=?;

# getUserByLogin
select
    user0_.id as id1_10_,
    user0_.modification_time as modifica2_10_,
    user0_.age as age3_10_,
    user0_.email as email4_10_,
    user0_.is_enabled as is_enabl5_10_,
    user0_.first_name as first_na6_10_,
    user0_.last_name as last_nam7_10_,
    user0_.login as login8_10_,
    user0_.password as password9_10_,
    user0_.phone as phone10_10_
from
    user user0_
where
        user0_.login=?;

# getUserByEmail
select
    user0_.id as id1_10_,
    user0_.modification_time as modifica2_10_,
    user0_.age as age3_10_,
    user0_.email as email4_10_,
    user0_.is_enabled as is_enabl5_10_,
    user0_.first_name as first_na6_10_,
    user0_.last_name as last_nam7_10_,
    user0_.login as login8_10_,
    user0_.password as password9_10_,
    user0_.phone as phone10_10_
from
    user user0_
where
        user0_.email=?;

# getUserByPhone
select
    user0_.id as id1_10_,
    user0_.modification_time as modifica2_10_,
    user0_.age as age3_10_,
    user0_.email as email4_10_,
    user0_.is_enabled as is_enabl5_10_,
    user0_.first_name as first_na6_10_,
    user0_.last_name as last_nam7_10_,
    user0_.login as login8_10_,
    user0_.password as password9_10_,
    user0_.phone as phone10_10_
from
    user user0_
where
        user0_.phone=?;

# getByToken
select
    usertoken0_.id as id1_9_,
    usertoken0_.token as token2_9_,
    usertoken0_.user_id as user_id3_9_
from
    token usertoken0_
where
        usertoken0_.token=?;