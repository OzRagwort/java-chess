create table member (
    id varchar(10) not null,
    name varchar(20) not null,
    primary key (id)
);

create table role (
    user_id varchar(10) not null,
    role varchar(10) not null,
    primary key (user_id),
    foreign key (user_id) references member (id)
);

--insert into member (id, name) values ('alien','wonyoung');
--insert into role (user_id, role) values ('alien','admin');
