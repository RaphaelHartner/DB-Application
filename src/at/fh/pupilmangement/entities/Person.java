package at.fh.pupilmangement.entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "concrete_person_type")
@DiscriminatorValue(value = "person")
public class Person {

	@SequenceGenerator(name = "PersonIdGenerator", sequenceName = "Person_Sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "PersonIdGenerator")
	private int id;
	private String firstName;
	private String lastName;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Temporal(value = TemporalType.DATE)
	private Date birthDate;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return "Person: " + getFirstName() + " " + getLastName() + ", "
				+ getBirthDate();
	}

}
