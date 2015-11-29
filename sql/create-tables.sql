-- ##### SEQUENCE #####

CREATE SEQUENCE Person_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE RoomType_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE Room_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE SchoolClass_Sequence MINVALUE 1 INCREMENT 1;

-- ##### Tables #####
-- Table: roomtype
CREATE TABLE roomtype
(
  id INTEGER NOT NULL DEFAULT nextval('RoomType_Sequence'),
  type character varying(100),
  CONSTRAINT roomtype_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE roomtype
  OWNER TO pupilmanagement;

-- Table: room
CREATE TABLE room
(
  id INTEGER NOT NULL DEFAULT nextval('Room_Sequence'),
  typeid integer,
  maxpupils integer,
  "position" character varying(20),
  concrete_room_type character varying, -- only needed for inheritance
  CONSTRAINT room_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE room
  OWNER TO pupilmanagement;

-- Table: classroom
CREATE TABLE classroom
(
  id INTEGER NOT NULL DEFAULT nextval('Room_Sequence'),
  --room_id integer,
  CONSTRAINT classroom_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE classroom
  OWNER TO pupilmanagement;

-- Table: schoolclass
CREATE TABLE schoolclass
(
  id INTEGER NOT NULL DEFAULT nextval('SchoolClass_Sequence'),
  name character varying(30),
  grade integer,
  classroom_id integer,
  classteacher_id integer,
  CONSTRAINT schoolclass_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE schoolclass
  OWNER TO pupilmanagement;
  
-- Table: person
CREATE TABLE person
(
  id INTEGER NOT NULL DEFAULT nextval('Person_Sequence'),
  firstname character varying(50),
  lastname character varying(50),
  birthdate date,
  concrete_person_type character varying, -- only needed for inheritance
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person
  OWNER TO pupilmanagement;

-- Table: pupil
CREATE TABLE pupil
(
  id INTEGER NOT NULL DEFAULT nextval('Person_Sequence'),
  yearofentry smallint,
  schoolclass_id integer,
  CONSTRAINT pupil_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pupil
  OWNER TO pupilmanagement;
 	  
-- Table: teacher
CREATE TABLE teacher
(
  id INTEGER NOT NULL DEFAULT nextval('Person_Sequence'),
  abbreviation character varying(10),
  CONSTRAINT teacher_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE teacher
  OWNER TO pupilmanagement;

  -- Table: classteacher
CREATE TABLE classteacher
(
  id INTEGER NOT NULL DEFAULT nextval('Person_Sequence'),
  CONSTRAINT classteacher_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE classteacher
  OWNER TO pupilmanagement;


  
-- ##### Mapping Tables #####
-- Table: schoolclass_room  
CREATE TABLE schoolclass_room
(
  schoolclasses_id integer,
  rooms_id integer,
  CONSTRAINT schoolclass_room_pkey PRIMARY KEY (schoolclasses_id, rooms_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE schoolclass_room
  OWNER TO pupilmanagement;

-- Table: schoolclass_teacher
CREATE TABLE schoolclass_teacher
(
  schoolclasses_id integer,
  teachers_id integer,
  CONSTRAINT schoolclass_teacher_pkey PRIMARY KEY (schoolclasses_id, teachers_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE schoolclass_teacher
  OWNER TO pupilmanagement;
  
	
-- ##### ADD FOREIGN-KEY CONTRAINTS #####

ALTER TABLE room ADD CONSTRAINT room_typeid_fkey FOREIGN KEY (typeid)
      REFERENCES roomtype (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE schoolclass ADD CONSTRAINT schoolclass_classroom_id_fkey FOREIGN KEY (classroom_id)
      REFERENCES classroom (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE schoolclass ADD CONSTRAINT schoolclass_classteacher_id_fkey FOREIGN KEY (classteacher_id)
      REFERENCES teacher (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
ALTER TABLE pupil ADD CONSTRAINT pupil_schoolclass_id_fkey FOREIGN KEY (schoolclass_id)
      REFERENCES schoolclass (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
ALTER TABLE schoolclass_room ADD CONSTRAINT schoolclass_room_id_fkey FOREIGN KEY (schoolclasses_id)
      REFERENCES schoolclass (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE schoolclass_room ADD CONSTRAINT schoolclass_room__id_fkey FOREIGN KEY (rooms_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
ALTER TABLE schoolclass_teacher ADD CONSTRAINT schoolclass_teacher_schoolclass_id_fkey FOREIGN KEY (schoolclasses_id)
      REFERENCES schoolclass (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE schoolclass_teacher ADD CONSTRAINT schoolclass_teacher_id_fkey FOREIGN KEY (teachers_id)
      REFERENCES teacher (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
   
