-- create role
--
--CREATE ROLE dev WITH LOGIN CREATEDB PASSWORD 'ax2';

-- create databases



-- DATABASE NEEDS TO BE CREATED MANUALLY BEFORE RUNNING THIS SCRIPT!!!!!


-- COPY PASTE THIS INTO A QUERY CONSOLE TO CREATE THE DATABASE AND TABLES


 DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'HobbyHub') THEN
        CREATE DATABASE "HobbyHub";
        RAISE NOTICE 'Database HobbyHub created.';
    ELSE
        RAISE NOTICE 'Database HobbyHub already exists.';
    END IF;

    -- Drop tables if the database already exists
    IF EXISTS (SELECT 1 FROM pg_database WHERE datname = 'HobbyHub') THEN
        DROP TABLE IF EXISTS public.zipcode, public.address, public.users, public.phone, public.hobby_user, public.hobby;
    END IF;
END $$;



\c HobbyHub

-- create tables

CREATE TABLE zipcode
(
    zip bigint PRIMARY KEY NOT NULL,
    city_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    region_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    municipality_name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL
);

CREATE TABLE address
(
    id bigint PRIMARY KEY NOT NULL,
    street_name character varying(255) ΩCOLLATE pg_catalog."default" DEFAULT NULL,
    street_number character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
    zip bigint CONSTRAINT KEY REFERENCES zipcode(zip)
);

   CREATE TABLE users
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





    CREATE TABLE phone
    (
        id bigint PRIMARY KEY NOT NULL,
        number character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
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
        id character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        wikiLink character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        category character varying(255) COLLATE pg_catalog."default" DEFAULT NULL,
        type character varying(255) COLLATE pg_catalog."default" DEFAULT NULL
    );


