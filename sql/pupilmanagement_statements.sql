--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-12-12 12:27:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE pupilmanagement;
--
-- TOC entry 2881 (class 1262 OID 22904)
-- Name: pupilmanagement; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE pupilmanagement WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect pupilmanagement

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 181 (class 3079 OID 12617)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 170 (class 1259 OID 23845)
-- Name: room_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE room_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 23853)
-- Name: classroom; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE classroom (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL
);


--
-- TOC entry 171 (class 1259 OID 23847)
-- Name: person_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE person_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 174 (class 1259 OID 23857)
-- Name: person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE person (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    birthdate date,
    concrete_person_type character varying,
    CONSTRAINT birthdate_in_past CHECK ((birthdate < ('now'::text)::date))
);


--
-- TOC entry 175 (class 1259 OID 23865)
-- Name: pupil; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE pupil (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    yearofentry smallint,
    schoolclass_id integer
);


--
-- TOC entry 176 (class 1259 OID 23869)
-- Name: room; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE room (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL,
    type character varying(50) NOT NULL,
    maxpupils integer,
    "position" character varying(20),
    concrete_room_type character varying
);


--
-- TOC entry 172 (class 1259 OID 23851)
-- Name: schoolclass_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE schoolclass_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 177 (class 1259 OID 23880)
-- Name: schoolclass; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE schoolclass (
    id integer DEFAULT nextval('schoolclass_sequence'::regclass) NOT NULL,
    name character varying(30),
    grade integer,
    classroom_id integer,
    classteacher_id integer
);


--
-- TOC entry 178 (class 1259 OID 23884)
-- Name: schoolclass_room; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE schoolclass_room (
    schoolclasses_id integer NOT NULL,
    rooms_id integer NOT NULL
);


--
-- TOC entry 179 (class 1259 OID 23887)
-- Name: schoolclass_teacher; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE schoolclass_teacher (
    schoolclasses_id integer NOT NULL,
    teachers_id integer NOT NULL
);


--
-- TOC entry 180 (class 1259 OID 23890)
-- Name: teacher; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE teacher (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    abbreviation character varying(10)
);


--
-- TOC entry 2869 (class 0 OID 23853)
-- Dependencies: 173
-- Data for Name: classroom; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO classroom (id) VALUES (1);


--
-- TOC entry 2870 (class 0 OID 23857)
-- Dependencies: 174
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 171
-- Name: person_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('person_sequence', 36, true);


--
-- TOC entry 2871 (class 0 OID 23865)
-- Dependencies: 175
-- Data for Name: pupil; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2872 (class 0 OID 23869)
-- Dependencies: 176
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO room (id, type, maxpupils, "position", concrete_room_type) VALUES (1, 'Klassenzimmer', 30, 'A.2.12', NULL);


--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 170
-- Name: room_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('room_sequence', 3, true);


--
-- TOC entry 2873 (class 0 OID 23880)
-- Dependencies: 177
-- Data for Name: schoolclass; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO schoolclass (id, name, grade, classroom_id, classteacher_id) VALUES (5, 'testsetnull', 3, NULL, NULL);


--
-- TOC entry 2874 (class 0 OID 23884)
-- Dependencies: 178
-- Data for Name: schoolclass_room; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 172
-- Name: schoolclass_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('schoolclass_sequence', 7, true);


--
-- TOC entry 2875 (class 0 OID 23887)
-- Dependencies: 179
-- Data for Name: schoolclass_teacher; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2876 (class 0 OID 23890)
-- Dependencies: 180
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2731 (class 2606 OID 23895)
-- Name: classroom_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 23897)
-- Name: id_schoolclass_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT id_schoolclass_unique UNIQUE (id, schoolclass_id);


--
-- TOC entry 2743 (class 2606 OID 23899)
-- Name: name_grade_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT name_grade_unique UNIQUE (name, grade);


--
-- TOC entry 2733 (class 2606 OID 23901)
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 23903)
-- Name: pupil_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 23905)
-- Name: room_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 23909)
-- Name: schoolclass_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 23911)
-- Name: schoolclass_room_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_pkey PRIMARY KEY (schoolclasses_id, rooms_id);


--
-- TOC entry 2749 (class 2606 OID 23913)
-- Name: schoolclass_teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_pkey PRIMARY KEY (schoolclasses_id, teachers_id);


--
-- TOC entry 2751 (class 2606 OID 23915)
-- Name: teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- TOC entry 2741 (class 2606 OID 23960)
-- Name: type_position_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY room
    ADD CONSTRAINT type_position_unique UNIQUE (type, "position");


--
-- TOC entry 2752 (class 2606 OID 23918)
-- Name: pupil_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_schoolclass_id_fkey FOREIGN KEY (schoolclass_id) REFERENCES schoolclass(id);


--
-- TOC entry 2753 (class 2606 OID 24045)
-- Name: schoolclass_classroom_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classroom_id_fkey FOREIGN KEY (classroom_id) REFERENCES classroom(id) ON DELETE SET NULL;


--
-- TOC entry 2754 (class 2606 OID 24050)
-- Name: schoolclass_classteacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classteacher_id_fkey FOREIGN KEY (classteacher_id) REFERENCES teacher(id) ON DELETE SET NULL;


--
-- TOC entry 2755 (class 2606 OID 24055)
-- Name: schoolclass_room__id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room__id_fkey FOREIGN KEY (rooms_id) REFERENCES room(id) ON DELETE CASCADE;


--
-- TOC entry 2756 (class 2606 OID 24060)
-- Name: schoolclass_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id) ON DELETE CASCADE;


--
-- TOC entry 2757 (class 2606 OID 24065)
-- Name: schoolclass_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_id_fkey FOREIGN KEY (teachers_id) REFERENCES teacher(id) ON DELETE CASCADE;


--
-- TOC entry 2758 (class 2606 OID 24070)
-- Name: schoolclass_teacher_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_schoolclass_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id) ON DELETE CASCADE;


-- Completed on 2015-12-12 12:27:04

--
-- PostgreSQL database dump complete
--

