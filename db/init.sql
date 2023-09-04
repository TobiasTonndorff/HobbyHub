-- create role

CREATE ROLE dev WITH LOGIN CREATEDB PASSWORD 'ax2';

-- create databases

CREATE DATABASE HubbyHub;

\c HubbyHub

-- create tables

   CREATE TABLE users
(
    id bigint PRIMARY KEY NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    birthdate date,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address bigint FOREIGN KEY REFERENCES address(id),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    updatedAt date,
    createdAt date,
   );

    CREATE TABLE address
    (
        id bigint PRIMARY KEY NOT NULL,
        street_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        street_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
        zip bigint FOREIGN KEY REFERENCES zipcode(zip),
    );

    CREATE TABLE zipcode
    (
        zip bigint PRIMARY KEY NOT NULL,
        city_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        region_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        municipality_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT zipcode_pkey PRIMARY KEY (zip),
    );

    CREATE TABLE phone
    (
        id bigint PRIMARY KEY NOT NULL,
        number character varying(255) COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT phone_pkey PRIMARY KEY (id),
        user_id bigint FOREIGN KEY REFERENCES users(id),
    );

    CREATE TABLE hobby_user
    (
        hobby_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        user_id bigint FOREIGN KEY REFERENCES users(id),
        CONSTRAINT hobby_user_pkey PRIMARY KEY (hobby_id, user_id),
    );


    CREATE TABLE hobby
    (
        id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        wikiLink character varying(255) COLLATE pg_catalog."default" NOT NULL,
        category ENUM('Generel', 'Educational hobbies', 'Samler hobbyer', 'Konkurrence', 'Observation') NOT NULL,
        type ENUM('Idendørs', 'Udendørs', 'Konkurrence', 'Observation', 'Samler hobbyer', 'Indendørs/Udendørs', '---') NOT NULL,
    );


