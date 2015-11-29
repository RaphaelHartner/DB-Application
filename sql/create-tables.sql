-- ##### SEQUENCE #####

CREATE SEQUENCE Person_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE RoomType_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE Room_Sequence MINVALUE 1 INCREMENT 1;
CREATE SEQUENCE Class_Sequence MINVALUE 1 INCREMENT 1;

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

-- Table: class
CREATE TABLE class
(
  id INTEGER NOT NULL DEFAULT nextval('Class_Sequence'),
  name character varying(30),
  grade integer,
  classroom_id integer,
  CONSTRAINT class_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE class
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
  -- person_id integer,
  class_id integer,
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
  -- person_id integer,
  CONSTRAINT teacher_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE teacher
  OWNER TO pupilmanagement;


  
-- ##### Mapping Tables #####
-- Table: occupancy
CREATE TABLE occupancy
(
  --id serial NOT NULL,
  class_id integer,
  room_id integer,
  CONSTRAINT occupancy_pkey PRIMARY KEY (class_id, room_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE occupancy
  OWNER TO pupilmanagement;

-- Table: tuition
CREATE TABLE tuition
(
  --id serial NOT NULL,
  class_id integer,
  teacher_id integer,
  isclassteacher bit(1) NOT NULL,
  CONSTRAINT tuition_pkey PRIMARY KEY (class_id, teacher_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tuition
  OWNER TO pupilmanagement;
  
	
-- ##### ADD FOREIGN-KEY CONTRAINTS #####

ALTER TABLE room ADD CONSTRAINT room_typeid_fkey FOREIGN KEY (typeid)
      REFERENCES roomtype (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

/*ALTER TABLE classroom ADD CONSTRAINT classroom_room_id_fkey FOREIGN KEY (room_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;*/

ALTER TABLE "class" ADD CONSTRAINT class_classroom_id_fkey FOREIGN KEY (classroom_id)
      REFERENCES classroom (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
/*ALTER TABLE teacher ADD CONSTRAINT teacher_person_id_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;*/

ALTER TABLE pupil ADD CONSTRAINT pupil_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
/*ALTER TABLE pupil ADD CONSTRAINT pupil_person_id_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;*/
	  
ALTER TABLE tuition ADD CONSTRAINT tuition_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE tuition ADD CONSTRAINT tuition_teacher_id_fkey FOREIGN KEY (teacher_id)
      REFERENCES teacher (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
ALTER TABLE occupancy ADD CONSTRAINT occupancy_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE occupancy ADD CONSTRAINT occupancy_room_id_fkey FOREIGN KEY (room_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
   
