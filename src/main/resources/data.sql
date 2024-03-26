create table lecture (
        lecture_id varchar(255) not null,
        current_enrollment integer,
        lecture_date timestamp(6) with time zone,
        max_enrollment integer,
        primary key (lecture_id)
);

create table enrollment (
            lecture_id varchar(255) not null,
            user_id varchar(255) not null,
            registration_status varchar(255) check (registration_status in ('REGISTERING','REGISTERED','FAILED')),
            primary key (lecture_id, user_id)
);

insert into lecture values(0,1,'2024-03-25 13:46:07.802371+09',1);