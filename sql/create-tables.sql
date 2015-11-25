-- create tables for db "pupilmanagement"

-- class
CREATE TABLE class
(
  id serial NOT NULL,
  name character varying(30),
  grade integer,
  classroom_id integer,
  CONSTRAINT class_pkey PRIMARY KEY (id),
  CONSTRAINT class_classroom_id_fkey FOREIGN KEY (classroom_id)
      REFERENCES classroom (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE class
  OWNER TO pupilmanagement;
  
  
-- room
CREATE TABLE room
(
  id serial NOT NULL,
  name character varying(30),
  maxpupils integer,
  "position" character varying(20),
  CONSTRAINT room_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE room
  OWNER TO pupilmanagement;

  
-- classroom
CREATE TABLE classroom
(
  id serial NOT NULL,
  room_id integer,
  CONSTRAINT classroom_pkey PRIMARY KEY (id),
  CONSTRAINT classroom_room_id_fkey FOREIGN KEY (room_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE classroom
  OWNER TO pupilmanagement;


-- occupancy --> mapping table between class and room
CREATE TABLE occupancy
(
  id serial NOT NULL,
  class_id integer,
  room_id integer,
  CONSTRAINT occupancy_pkey PRIMARY KEY (id),
  CONSTRAINT occupancy_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT occupancy_room_id_fkey FOREIGN KEY (room_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE occupancy
  OWNER TO pupilmanagement;


-- person
CREATE TABLE person
(
  id serial NOT NULL,
  firstname character varying(50),
  lastname character varying(50),
  birthdate date,
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person
  OWNER TO pupilmanagement;


-- pupil
CREATE TABLE pupil
(
  id serial NOT NULL,
  yearofentry smallint,
  person_id integer,
  class_id integer,
  CONSTRAINT pupil_pkey PRIMARY KEY (id),
  CONSTRAINT pupil_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pupil_person_id_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pupil
  OWNER TO pupilmanagement;


-- teacher
CREATE TABLE teacher
(
  id serial NOT NULL,
  abbreviation character varying(10),
  person_id integer,
  CONSTRAINT teacher_pkey PRIMARY KEY (id),
  CONSTRAINT teacher_person_id_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE teacher
  OWNER TO pupilmanagement;

  
-- tuition --> mapping table between teacher and class
-- there is also a flag which is true if the current teacher is the class teacher --> "isclassteacher"
CREATE TABLE tuition
(
  id serial NOT NULL,
  class_id integer,
  teacher_id integer,
  isclassteacher bit(1) NOT NULL,
  CONSTRAINT tuition_pkey PRIMARY KEY (id),
  CONSTRAINT tuition_class_id_fkey FOREIGN KEY (class_id)
      REFERENCES class (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tuition_teacher_id_fkey FOREIGN KEY (teacher_id)
      REFERENCES teacher (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tuition
  OWNER TO pupilmanagement;
