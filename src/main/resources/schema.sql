drop table if exists public.book;

create table public.book
(
    id          serial primary key,
    description text,
    isbn        character varying(255),
    page        integer          NOT NULL,
    price       double precision NOT NULL,
    title       character varying(100)
);