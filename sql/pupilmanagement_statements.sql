--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2016-01-17 20:24:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 24077)
-- Name: pupilmanagement; Type: SCHEMA; Schema: -; Owner: pupilmanagement
--

CREATE SCHEMA pupilmanagement;


ALTER SCHEMA pupilmanagement OWNER TO pupilmanagement;

--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA pupilmanagement; Type: COMMENT; Schema: -; Owner: pupilmanagement
--

COMMENT ON SCHEMA pupilmanagement IS 'schema for the pupil management project';


SET search_path = pupilmanagement, pg_catalog;

--
-- TOC entry 171 (class 1259 OID 24078)
-- Name: room_sequence; Type: SEQUENCE; Schema: pupilmanagement; Owner: pupilmanagement
--

CREATE SEQUENCE room_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pupilmanagement.room_sequence OWNER TO pupilmanagement;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 24080)
-- Name: classroom; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE classroom (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL
);


ALTER TABLE pupilmanagement.classroom OWNER TO pupilmanagement;

--
-- TOC entry 173 (class 1259 OID 24084)
-- Name: person_sequence; Type: SEQUENCE; Schema: pupilmanagement; Owner: pupilmanagement
--

CREATE SEQUENCE person_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pupilmanagement.person_sequence OWNER TO pupilmanagement;

--
-- TOC entry 174 (class 1259 OID 24086)
-- Name: person; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE person (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    birthdate date,
    concrete_person_type character varying,
    CONSTRAINT birthdate_in_past CHECK ((birthdate < ('now'::text)::date))
);


ALTER TABLE pupilmanagement.person OWNER TO pupilmanagement;

--
-- TOC entry 175 (class 1259 OID 24094)
-- Name: pupil; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE pupil (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    yearofentry smallint,
    schoolclass_id integer
);


ALTER TABLE pupilmanagement.pupil OWNER TO pupilmanagement;

--
-- TOC entry 176 (class 1259 OID 24098)
-- Name: room; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE room (
    id integer DEFAULT nextval('room_sequence'::regclass) NOT NULL,
    type character varying(50) NOT NULL,
    maxpupils integer,
    "position" character varying(20),
    concrete_room_type character varying
);


ALTER TABLE pupilmanagement.room OWNER TO pupilmanagement;

--
-- TOC entry 177 (class 1259 OID 24105)
-- Name: schoolclass_sequence; Type: SEQUENCE; Schema: pupilmanagement; Owner: pupilmanagement
--

CREATE SEQUENCE schoolclass_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pupilmanagement.schoolclass_sequence OWNER TO pupilmanagement;

--
-- TOC entry 178 (class 1259 OID 24107)
-- Name: schoolclass; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass (
    id integer DEFAULT nextval('schoolclass_sequence'::regclass) NOT NULL,
    name character varying(30),
    grade integer,
    classroom_id integer,
    classteacher_id integer
);


ALTER TABLE pupilmanagement.schoolclass OWNER TO pupilmanagement;

--
-- TOC entry 179 (class 1259 OID 24111)
-- Name: schoolclass_room; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass_room (
    schoolclasses_id integer NOT NULL,
    rooms_id integer NOT NULL
);


ALTER TABLE pupilmanagement.schoolclass_room OWNER TO pupilmanagement;

--
-- TOC entry 180 (class 1259 OID 24114)
-- Name: schoolclass_teacher; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE schoolclass_teacher (
    schoolclasses_id integer NOT NULL,
    teachers_id integer NOT NULL
);


ALTER TABLE pupilmanagement.schoolclass_teacher OWNER TO pupilmanagement;

--
-- TOC entry 181 (class 1259 OID 24117)
-- Name: teacher; Type: TABLE; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

CREATE TABLE teacher (
    id integer DEFAULT nextval('person_sequence'::regclass) NOT NULL,
    abbreviation character varying(10)
);


ALTER TABLE pupilmanagement.teacher OWNER TO pupilmanagement;

--
-- TOC entry 2872 (class 0 OID 24080)
-- Dependencies: 172
-- Data for Name: classroom; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2874 (class 0 OID 24086)
-- Dependencies: 174
-- Data for Name: person; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 173
-- Name: person_sequence; Type: SEQUENCE SET; Schema: pupilmanagement; Owner: pupilmanagement
--

SELECT pg_catalog.setval('person_sequence', 284, true);


--
-- TOC entry 2875 (class 0 OID 24094)
-- Dependencies: 175
-- Data for Name: pupil; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2876 (class 0 OID 24098)
-- Dependencies: 176
-- Data for Name: room; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 171
-- Name: room_sequence; Type: SEQUENCE SET; Schema: pupilmanagement; Owner: pupilmanagement
--

SELECT pg_catalog.setval('room_sequence', 273, true);


--
-- TOC entry 2878 (class 0 OID 24107)
-- Dependencies: 178
-- Data for Name: schoolclass; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2879 (class 0 OID 24111)
-- Dependencies: 179
-- Data for Name: schoolclass_room; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 177
-- Name: schoolclass_sequence; Type: SEQUENCE SET; Schema: pupilmanagement; Owner: pupilmanagement
--

SELECT pg_catalog.setval('schoolclass_sequence', 293, true);


--
-- TOC entry 2880 (class 0 OID 24114)
-- Dependencies: 180
-- Data for Name: schoolclass_teacher; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2881 (class 0 OID 24117)
-- Dependencies: 181
-- Data for Name: teacher; Type: TABLE DATA; Schema: pupilmanagement; Owner: pupilmanagement
--



--
-- TOC entry 2744 (class 2606 OID 24211)
-- Name: classroom_id_unique; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT classroom_id_unique UNIQUE (classroom_id);


--
-- TOC entry 2732 (class 2606 OID 24122)
-- Name: classroom_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (id);


--
-- TOC entry 2746 (class 2606 OID 24213)
-- Name: classteacher_id_unique; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT classteacher_id_unique UNIQUE (classteacher_id);


--
-- TOC entry 2736 (class 2606 OID 24124)
-- Name: id_schoolclass_unique; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT id_schoolclass_unique UNIQUE (id, schoolclass_id);


--
-- TOC entry 2748 (class 2606 OID 24207)
-- Name: name_grade_unique; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT name_grade_unique UNIQUE (name, grade);


--
-- TOC entry 2734 (class 2606 OID 24128)
-- Name: person_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 24130)
-- Name: pupil_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_pkey PRIMARY KEY (id);


--
-- TOC entry 2740 (class 2606 OID 24132)
-- Name: room_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- TOC entry 2750 (class 2606 OID 24134)
-- Name: schoolclass_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 24136)
-- Name: schoolclass_room_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_pkey PRIMARY KEY (schoolclasses_id, rooms_id);


--
-- TOC entry 2754 (class 2606 OID 24138)
-- Name: schoolclass_teacher_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_pkey PRIMARY KEY (schoolclasses_id, teachers_id);


--
-- TOC entry 2756 (class 2606 OID 24140)
-- Name: teacher_pkey; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- TOC entry 2742 (class 2606 OID 24209)
-- Name: type_position_unique; Type: CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT type_position_unique UNIQUE (type, "position");


--
-- TOC entry 2757 (class 2606 OID 24143)
-- Name: pupil_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_schoolclass_id_fkey FOREIGN KEY (schoolclass_id) REFERENCES schoolclass(id);


--
-- TOC entry 2758 (class 2606 OID 24148)
-- Name: schoolclass_classroom_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classroom_id_fkey FOREIGN KEY (classroom_id) REFERENCES classroom(id) ON DELETE SET NULL;


--
-- TOC entry 2759 (class 2606 OID 24153)
-- Name: schoolclass_classteacher_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass
    ADD CONSTRAINT schoolclass_classteacher_id_fkey FOREIGN KEY (classteacher_id) REFERENCES teacher(id) ON DELETE SET NULL;


--
-- TOC entry 2760 (class 2606 OID 24158)
-- Name: schoolclass_room__id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room__id_fkey FOREIGN KEY (rooms_id) REFERENCES room(id) ON DELETE CASCADE;


--
-- TOC entry 2761 (class 2606 OID 24163)
-- Name: schoolclass_room_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_room
    ADD CONSTRAINT schoolclass_room_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id) ON DELETE CASCADE;


--
-- TOC entry 2762 (class 2606 OID 24168)
-- Name: schoolclass_teacher_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_id_fkey FOREIGN KEY (teachers_id) REFERENCES teacher(id) ON DELETE CASCADE;


--
-- TOC entry 2763 (class 2606 OID 24173)
-- Name: schoolclass_teacher_schoolclass_id_fkey; Type: FK CONSTRAINT; Schema: pupilmanagement; Owner: pupilmanagement
--

ALTER TABLE ONLY schoolclass_teacher
    ADD CONSTRAINT schoolclass_teacher_schoolclass_id_fkey FOREIGN KEY (schoolclasses_id) REFERENCES schoolclass(id) ON DELETE CASCADE;


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 6
-- Name: pupilmanagement; Type: ACL; Schema: -; Owner: pupilmanagement
--

REVOKE ALL ON SCHEMA pupilmanagement FROM PUBLIC;
REVOKE ALL ON SCHEMA pupilmanagement FROM pupilmanagement;
GRANT ALL ON SCHEMA pupilmanagement TO pupilmanagement;
GRANT ALL ON SCHEMA pupilmanagement TO pupilmanagement_admin_role;
GRANT USAGE ON SCHEMA pupilmanagement TO pupilmanagement_reader_role;


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 171
-- Name: room_sequence; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON SEQUENCE room_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE room_sequence FROM pupilmanagement;
GRANT ALL ON SEQUENCE room_sequence TO pupilmanagement;
GRANT SELECT ON SEQUENCE room_sequence TO pupilmanagement_reader_role;
GRANT UPDATE ON SEQUENCE room_sequence TO pupilmanagement_writer_role;


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 172
-- Name: classroom; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE classroom FROM PUBLIC;
REVOKE ALL ON TABLE classroom FROM pupilmanagement;
GRANT ALL ON TABLE classroom TO pupilmanagement;
GRANT SELECT ON TABLE classroom TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE classroom TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE classroom TO pupilmanagement_admin_role;


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 173
-- Name: person_sequence; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON SEQUENCE person_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE person_sequence FROM pupilmanagement;
GRANT ALL ON SEQUENCE person_sequence TO pupilmanagement;
GRANT SELECT ON SEQUENCE person_sequence TO pupilmanagement_reader_role;
GRANT UPDATE ON SEQUENCE person_sequence TO pupilmanagement_writer_role;


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 174
-- Name: person; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE person FROM PUBLIC;
REVOKE ALL ON TABLE person FROM pupilmanagement;
GRANT ALL ON TABLE person TO pupilmanagement;
GRANT SELECT ON TABLE person TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE person TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE person TO pupilmanagement_admin_role;


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 175
-- Name: pupil; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE pupil FROM PUBLIC;
REVOKE ALL ON TABLE pupil FROM pupilmanagement;
GRANT ALL ON TABLE pupil TO pupilmanagement;
GRANT SELECT ON TABLE pupil TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE pupil TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE pupil TO pupilmanagement_admin_role;


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 176
-- Name: room; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE room FROM PUBLIC;
REVOKE ALL ON TABLE room FROM pupilmanagement;
GRANT ALL ON TABLE room TO pupilmanagement;
GRANT SELECT ON TABLE room TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE room TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE room TO pupilmanagement_admin_role;


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 177
-- Name: schoolclass_sequence; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON SEQUENCE schoolclass_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE schoolclass_sequence FROM pupilmanagement;
GRANT ALL ON SEQUENCE schoolclass_sequence TO pupilmanagement;
GRANT SELECT ON SEQUENCE schoolclass_sequence TO pupilmanagement_reader_role;
GRANT UPDATE ON SEQUENCE schoolclass_sequence TO pupilmanagement_writer_role;


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 178
-- Name: schoolclass; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE schoolclass FROM PUBLIC;
REVOKE ALL ON TABLE schoolclass FROM pupilmanagement;
GRANT ALL ON TABLE schoolclass TO pupilmanagement;
GRANT SELECT ON TABLE schoolclass TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE schoolclass TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE schoolclass TO pupilmanagement_admin_role;


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 179
-- Name: schoolclass_room; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE schoolclass_room FROM PUBLIC;
REVOKE ALL ON TABLE schoolclass_room FROM pupilmanagement;
GRANT ALL ON TABLE schoolclass_room TO pupilmanagement;
GRANT SELECT ON TABLE schoolclass_room TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE schoolclass_room TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE schoolclass_room TO pupilmanagement_admin_role;


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 180
-- Name: schoolclass_teacher; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE schoolclass_teacher FROM PUBLIC;
REVOKE ALL ON TABLE schoolclass_teacher FROM pupilmanagement;
GRANT ALL ON TABLE schoolclass_teacher TO pupilmanagement;
GRANT SELECT ON TABLE schoolclass_teacher TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE schoolclass_teacher TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE schoolclass_teacher TO pupilmanagement_admin_role;


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 181
-- Name: teacher; Type: ACL; Schema: pupilmanagement; Owner: pupilmanagement
--

REVOKE ALL ON TABLE teacher FROM PUBLIC;
REVOKE ALL ON TABLE teacher FROM pupilmanagement;
GRANT ALL ON TABLE teacher TO pupilmanagement;
GRANT SELECT ON TABLE teacher TO pupilmanagement_reader_role;
GRANT INSERT,UPDATE ON TABLE teacher TO pupilmanagement_writer_role;
GRANT DELETE ON TABLE teacher TO pupilmanagement_admin_role;


-- Completed on 2016-01-17 20:24:46

--
-- PostgreSQL database dump complete
--

