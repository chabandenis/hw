DROP TABLE if EXISTS public.eggs CASCADE;

create table eggs (
    id varchar(32) not null,
    name varchar(255) not null,
    primary key (id)
);