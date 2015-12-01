--
-- PostgreSQL database dump
--
--Create Database 
/*
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE pupilmanagement;
CREATE DATABASE pupilmanagement WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect pupilmanagement

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE SCHEMA public;

COMMENT ON SCHEMA public IS 'standard public schema';
CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;
SET default_with_oids = false;
*/

-- ###### Create Sequences ######
CREATE SEQUENCE room_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

	CREATE SEQUENCE person_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE roomtype_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
CREATE SEQUENCE schoolclass_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



-- ###### Create Tables ######
CREATE TABLE classroom (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL
);

CREATE TABLE person (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    birthdate date,
    concrete_person_type character varying,
    CONSTRAINT birthdate_in_past CHECK ((birthdate < ('now'::text)::date))
);

CREATE TABLE pupil (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    yearofentry smallint,
    schoolclass_id integer
);

CREATE TABLE room (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL,
    typeid integer NOT NULL,
    maxpupils integer,
    "position" character varying(20),
    concrete_room_type character varying
);

CREATE TABLE roomtype (
    id integer DEFAULT nextval('roomtype_sequence'::regclass) NOT NULL,
    type character varying(100)
);

CREATE TABLE schoolclass (
    id integer DEFAULT nextval('schoolclass_sequence'::regclass) NOT NULL,
    name character varying(30),
    grade integer,
    classroom_id integer,
    classteacher_id integer
);

CREATE TABLE schoolclass_room (
    schoolclasses_id integer NOT NULL,
    rooms_id integer NOT NULL
);

CREATE TABLE schoolclass_teacher (
    schoolclasses_id integer NOT NULL,
    teachers_id integer NOT NULL
);

CREATE TABLE teacher (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    abbreviation character varying(10)
);


-- ###### Alter Tables ######

ALTER TABLE ONLY classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (id);

ALTER TABLE ONLY pupil
    ADD CONSTRAINT id_schoolclass_unique UNIQUE (id, schoolclass_id);

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT name_grade_unique UNIQUE (name, grade);

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_pkey PRIMARY KEY (id);

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);

ALTER TABLE ONLY roomtype
    ADD CONSTRAINT roomtype_pkey PRIMARY KEY (id);

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_pkey PRIMARY KEY (id);

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_pkey PRIMARY KEY (schoolclasses_id, rooms_id);

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_pkey PRIMARY KEY (schoolclasses_id, teachers_id);

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);

ALTER TABLE ONLY room
    ADD CONSTRAINT type_position_unique UNIQUE (typeid, "position");

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_schoolclass_id_fkey FOREIGN KEY (schoolclass_id) REFERENCES schoolclass(id);

ALTER TABLE ONLY room
    ADD CONSTRAINT room_typeid_fkey FOREIGN KEY (typeid) REFERENCES roomtype(id);

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classroom_id_fkey FOREIGN KEY (classroom_id) REFERENCES classroom(id);

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classteacher_id_fkey FOREIGN KEY (classteacher_id) REFERENCES teacher(id);

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room__id_fkey FOREIGN KEY (rooms_id) REFERENCES room(id);

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id);

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_id_fkey FOREIGN KEY (teachers_id) REFERENCES teacher(id);

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_schoolclass_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id);