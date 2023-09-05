-- create role
--
--CREATE ROLE dev WITH LOGIN CREATEDB PASSWORD 'ax2';

-- create databases





 DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'HubbyHub') THEN
        CREATE DATABASE "HubbyHub";
        RAISE NOTICE 'Database HubbyHub created.';
    ELSE
        RAISE NOTICE 'Database HubbyHub already exists.';
    END IF;

    -- Drop tables if the database already exists
    IF EXISTS (SELECT 1 FROM pg_database WHERE datname = 'HubbyHub') THEN
        DROP TABLE IF EXISTS public.zipcode, public.address, public.users, public.phone, public.hobby_user, public.hobby;
    END IF;
END $$;



\c HubbyHub

-- create tables

CREATE TABLE public.zipcode
(
    zip bigint PRIMARY KEY NOT NULL,
    city_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    region_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    municipality_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL
);

CREATE TABLE public.address
(
    id bigint PRIMARY KEY NOT NULL,
    street_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    street_number character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    zip bigint CONSTRAINT KEY REFERENCES zipcode(zip)
);

   CREATE TABLE public.users
(
    id bigint PRIMARY KEY NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    surname character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    birthdate date,
    email character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    address bigint CONSTRAINT KEY REFERENCES address(id),
    updatedAt date,
    createdAt date
   );





    CREATE TABLE public.phone
    (
        id bigint PRIMARY KEY NOT NULL,
        number character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        user_id bigint CONSTRAINT KEY REFERENCES users(id)
    );

    CREATE TABLE public.hobby_user
    (
        hobby_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        user_id bigint CONSTRAINT KEY REFERENCES users(id),
        CONSTRAINT hobby_user_pkey PRIMARY KEY (hobby_id, user_id)
    );


    CREATE TABLE public.hobby
    (
        id character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        wikiLink character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        category character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        type character varying(255) COLLATE pg_catalog."default" DEFAULT NULL
    );


