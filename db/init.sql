-- create role
--
--CREATE ROLE dev WITH LOGIN CREATEDB PASSWORD 'ax2';

-- create databases

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'HubbyHub') THEN
            CREATE DATABASE "hubbyHub";
            RAISE NOTICE 'Database HubbyHub created.';
            ELSE
            RAISE NOTICE 'Database HubbyHub already exists.';
            DROP TABLE IF EXISTS public.zipcode, public.address, public.users, public.phone, public.hobby_user, public.hobby;
        END IF;
    END $$;

\c HubbyHub

-- create tables

CREATE TABLE zipcode
(
    zip bigint PRIMARY KEY NOT NULL,
    city_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    region_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    municipality_name character varying(255) COLLATE pg_catalog."default" NOT NULL
);

CREATE TABLE address
(
    id bigint PRIMARY KEY NOT NULL,
    street_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    zip bigint CONSTRAINT KEY REFERENCES zipcode(zip)
);

   CREATE TABLE users
(
    id bigint PRIMARY KEY NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    birthdate date,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address bigint CONSTRAINT KEY REFERENCES address(id),
    updatedAt date,
    createdAt date
   );





    CREATE TABLE phone
    (
        id bigint PRIMARY KEY NOT NULL,
        number character varying(255) COLLATE pg_catalog."default" NOT NULL,
        user_id bigint CONSTRAINT KEY REFERENCES users(id)
    );

    CREATE TABLE hobby_user
    (
        hobby_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        user_id bigint CONSTRAINT KEY REFERENCES users(id),
        CONSTRAINT hobby_user_pkey PRIMARY KEY (hobby_id, user_id)
    );


    CREATE TABLE hobby
    (
        id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        wikiLink character varying(255) COLLATE pg_catalog."default" NOT NULL,
        category character varying(255) COLLATE pg_catalog."default" NOT NULL,
        type character varying(255) COLLATE pg_catalog."default" NOT NULL
    );


