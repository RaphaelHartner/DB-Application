	
-- ###### Insert statements ######
INSERT INTO person (id, firstname, lastname, birthdate, concrete_person_type) VALUES (1, 'Nico', 'Mustermann', '1964-12-11', 'teacher');
INSERT INTO person (id, firstname, lastname, birthdate, concrete_person_type) VALUES (3, 'Herbert', 'Mayer', '2015-01-01', NULL);

SELECT pg_catalog.setval('person_sequence', 3, true);

INSERT INTO room (id, typeid, maxpupils, "position", concrete_room_type) VALUES (1, 1, 30, 'A.2.12', NULL);

SELECT pg_catalog.setval('room_sequence', 1, true);

INSERT INTO roomtype (id, type) VALUES (1, 'Klassenzimmer');

SELECT pg_catalog.setval('roomtype_sequence', 1, true);

INSERT INTO schoolclass (id, name, grade, classroom_id, classteacher_id) VALUES (1, 'b', 2, NULL, 1);

SELECT pg_catalog.setval('schoolclass_sequence', 1, true);

INSERT INTO schoolclass_teacher (schoolclasses_id, teachers_id) VALUES (1, 1);

INSERT INTO teacher (id, abbreviation) VALUES (1, 'MusN');