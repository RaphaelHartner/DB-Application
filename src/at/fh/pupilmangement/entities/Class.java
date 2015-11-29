package at.fh.pupilmangement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Class")
public class Class {

	@SequenceGenerator(name = "ClassIdGenerator", sequenceName = "Class_Sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "ClassIdGenerator")
	private int id;
}
