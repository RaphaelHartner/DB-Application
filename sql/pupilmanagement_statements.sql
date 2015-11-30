--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-11-30 19:55:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE pupilmanagement;
--
-- TOC entry 2901 (class 1262 OID 22904)
-- Name: pupilmanagement; Type: DATABASE; Schema: -; Owner: pupilmanagement
--

CREATE DATABASE pupilmanagement WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE pupilmanagement OWNER TO pupilmanagement;

\connect pupilmanagement

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 184 (class 3079 OID 12617)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 184
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 172 (class 1259 OID 23728)
-- Name: room_sequence; Type: SEQUENCE; Schema: public; Owner: pupilmanagement
--

CREATE SEQUENCE room_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_sequence OWNER TO pupilmanagement;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 23747)
-- Name: classroom; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE classroom (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL
);


ALTER TABLE public.classroom OWNER TO pupilmanagement;

--
-- TOC entry 170 (class 1259 OID 23724)
-- Name: person_sequence; Type: SEQUENCE; Schema: public; Owner: pupilmanagement
--

CREATE SEQUENCE person_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_sequence OWNER TO pupilmanagement;

--
-- TOC entry 181 (class 1259 OID 23780)
-- Name: classteacher; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE classteacher (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL
);


ALTER TABLE public.classteacher OWNER TO pupilmanagement;

--
-- TOC entry 178 (class 1259 OID 23759)
-- Name: person; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE person (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    birthdate date,
    concrete_person_type character varying,
    CONSTRAINT birthdate_in_past CHECK ((birthdate < ('now'::text)::date))
);


ALTER TABLE public.person OWNER TO pupilmanagement;

--
-- TOC entry 179 (class 1259 OID 23768)
-- Name: pupil; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE pupil (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    yearofentry smallint,
    schoolclass_id integer
);


ALTER TABLE public.pupil OWNER TO pupilmanagement;

--
-- TOC entry 175 (class 1259 OID 23738)
-- Name: room; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE room (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL,
    typeid integer NOT NULL,
    maxpupils integer,
    "position" character varying(20),
    concrete_room_type character varying
);


ALTER TABLE public.room OWNER TO pupilmanagement;

--
-- TOC entry 171 (class 1259 OID 23726)
-- Name: roomtype_sequence; Type: SEQUENCE; Schema: public; Owner: pupilmanagement
--

CREATE SEQUENCE roomtype_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roomtype_sequence OWNER TO pupilmanagement;

--
-- TOC entry 174 (class 1259 OID 23732)
-- Name: roomtype; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE roomtype (
    id integer DEFAULT nextval('roomtype_sequence'::regclass) NOT NULL,
    type character varying(100)
);


ALTER TABLE public.roomtype OWNER TO pupilmanagement;

--
-- TOC entry 173 (class 1259 OID 23730)
-- Name: schoolclass_sequence; Type: SEQUENCE; Schema: public; Owner: pupilmanagement
--

CREATE SEQUENCE schoolclass_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schoolclass_sequence OWNER TO pupilmanagement;

--
-- TOC entry 177 (class 1259 OID 23753)
-- Name: schoolclass; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass (
    id integer DEFAULT nextval('schoolclass_sequence'::regclass) NOT NULL,
    name character varying(30),
    grade integer,
    classroom_id integer,
    classteacher_id integer
);


ALTER TABLE public.schoolclass OWNER TO pupilmanagement;

--
-- TOC entry 182 (class 1259 OID 23786)
-- Name: schoolclass_room; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass_room (
    schoolclasses_id integer NOT NULL,
    rooms_id integer NOT NULL
);


ALTER TABLE public.schoolclass_room OWNER TO pupilmanagement;

--
-- TOC entry 183 (class 1259 OID 23791)
-- Name: schoolclass_teacher; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass_teacher (
    schoolclasses_id integer NOT NULL,
    teachers_id integer NOT NULL
);


ALTER TABLE public.schoolclass_teacher OWNER TO pupilmanagement;

--
-- TOC entry 180 (class 1259 OID 23774)
-- Name: teacher; Type: TABLE; Schema: public; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE teacher (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    abbreviation character varying(10)
);


ALTER TABLE public.teacher OWNER TO pupilmanagement;

--
-- TOC entry 2889 (class 0 OID 23747)
-- Dependencies: 176
-- Data for Name: classroom; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--



--
-- TOC entry 2894 (class 0 OID 23780)
-- Dependencies: 181
-- Data for Name: classteacher; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--



--
-- TOC entry 2891 (class 0 OID 23759)
-- Dependencies: 178
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO person (id, firstname, lastname, birthdate, concrete_person_type) VALUES (1, 'Nico', 'Mustermann', '1964-12-11', 'teacher');
INSERT INTO person (id, firstname, lastname, birthdate, concrete_person_type) VALUES (3, 'Herbert', 'Mayer', '2015-01-01', NULL);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 170
-- Name: person_sequence; Type: SEQUENCE SET; Schema: public; Owner: pupilmanagement
--

SELECT pg_catalog.setval('person_sequence', 3, true);


--
-- TOC entry 2892 (class 0 OID 23768)
-- Dependencies: 179
-- Data for Name: pupil; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--



--
-- TOC entry 2888 (class 0 OID 23738)
-- Dependencies: 175
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO room (id, typeid, maxpupils, "position", concrete_room_type) VALUES (1, 1, 30, 'A.2.12', NULL);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 172
-- Name: room_sequence; Type: SEQUENCE SET; Schema: public; Owner: pupilmanagement
--

SELECT pg_catalog.setval('room_sequence', 1, true);


--
-- TOC entry 2887 (class 0 OID 23732)
-- Dependencies: 174
-- Data for Name: roomtype; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO roomtype (id, type) VALUES (1, 'Klassenzimmer');


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 171
-- Name: roomtype_sequence; Type: SEQUENCE SET; Schema: public; Owner: pupilmanagement
--

SELECT pg_catalog.setval('roomtype_sequence', 1, true);


--
-- TOC entry 2890 (class 0 OID 23753)
-- Dependencies: 177
-- Data for Name: schoolclass; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO schoolclass (id, name, grade, classroom_id, classteacher_id) VALUES (1, 'b', 2, NULL, 1);


--
-- TOC entry 2895 (class 0 OID 23786)
-- Dependencies: 182
-- Data for Name: schoolclass_room; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--



--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 173
-- Name: schoolclass_sequence; Type: SEQUENCE SET; Schema: public; Owner: pupilmanagement
--

SELECT pg_catalog.setval('schoolclass_sequence', 1, true);


--
-- TOC entry 2896 (class 0 OID 23791)
-- Dependencies: 183
-- Data for Name: schoolclass_teacher; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO schoolclass_teacher (schoolclasses_id, teachers_id) VALUES (1, 1);


--
-- TOC entry 2893 (class 0 OID 23774)
-- Dependencies: 180
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: pupilmanagement
--

INSERT INTO teacher (id, abbreviation) VALUES (1, 'MusN');


--
-- TOC entry 2749 (class 2606 OID 23752)
-- Name: classroom_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (id);


--
-- TOC entry 2763 (class 2606 OID 23785)
-- Name: classteacher_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY classteacher
    ADD CONSTRAINT classteacher_pkey PRIMARY KEY (id);


--
-- TOC entry 2757 (class 2606 OID 23840)
-- Name: id_schoolclass_unique; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT id_schoolclass_unique UNIQUE (id, schoolclass_id);


--
-- TOC entry 2751 (class 2606 OID 23844)
-- Name: name_grade_unique; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT name_grade_unique UNIQUE (name, grade);


--
-- TOC entry 2755 (class 2606 OID 23767)
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2759 (class 2606 OID 23773)
-- Name: pupil_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 23746)
-- Name: room_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- TOC entry 2743 (class 2606 OID 23737)
-- Name: roomtype_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY roomtype
    ADD CONSTRAINT roomtype_pkey PRIMARY KEY (id);


--
-- TOC entry 2753 (class 2606 OID 23758)
-- Name: schoolclass_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 23790)
-- Name: schoolclass_room_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_pkey PRIMARY KEY (schoolclasses_id, rooms_id);


--
-- TOC entry 2767 (class 2606 OID 23795)
-- Name: schoolclass_teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_pkey PRIMARY KEY (schoolclasses_id, teachers_id);


--
-- TOC entry 2761 (class 2606 OID 23779)
-- Name: teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 23842)
-- Name: type_position_unique; Type: CONSTRAINT; Schema: public; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT type_position_unique UNIQUE (typeid, "position");


--
-- TOC entry 2771 (class 2606 OID 23811)
-- Name: pupil_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_schoolclass_id_fkey FOREIGN KEY (schoolclass_id) REFERENCES schoolclass(id);


--
-- TOC entry 2768 (class 2606 OID 23796)
-- Name: room_typeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_typeid_fkey FOREIGN KEY (typeid) REFERENCES roomtype(id);


--
-- TOC entry 2769 (class 2606 OID 23801)
-- Name: schoolclass_classroom_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classroom_id_fkey FOREIGN KEY (classroom_id) REFERENCES classroom(id);


--
-- TOC entry 2770 (class 2606 OID 23806)
-- Name: schoolclass_classteacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classteacher_id_fkey FOREIGN KEY (classteacher_id) REFERENCES teacher(id);


--
-- TOC entry 2773 (class 2606 OID 23821)
-- Name: schoolclass_room__id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room__id_fkey FOREIGN KEY (rooms_id) REFERENCES room(id);


--
-- TOC entry 2772 (class 2606 OID 23816)
-- Name: schoolclass_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id);


--
-- TOC entry 2775 (class 2606 OID 23831)
-- Name: schoolclass_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_id_fkey FOREIGN KEY (teachers_id) REFERENCES teacher(id);


--
-- TOC entry 2774 (class 2606 OID 23826)
-- Name: schoolclass_teacher_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_schoolclass_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id);


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-11-30 19:56:01

--
-- PostgreSQL database dump complete
--

