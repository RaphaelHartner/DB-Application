package at.fh.pupilmanagement.entities;

import java.util.Calendar;
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
public class Person extends BaseEntity{

	private static final String PERSON_SEQUENCE = "person_sequence";
	
	@Id
	@GeneratedValue(generator = "PersonIdGenerator")
	@SequenceGenerator(name = "PersonIdGenerator", sequenceName = PERSON_SEQUENCE, allocationSize = 1)
	private long id;
	private String firstName;
	private String lastName;
	
	public Person(){}
	
	public Person(String firstName, String lastName, Date birthDate){
		setFirstName(firstName);
		setLastName(lastName);
		setBirthDate(birthDate);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public void setBirthDate(Calendar calendar) {
		setBirthDate(calendar.getTime());
	}
	
	@Override
	public BaseEntity createClonedEntity() {
		return new Person(getFirstName(), getLastName(), getBirthDate());
	}
	
	@Override
	public String toString() {
		return "Person: " + getFirstName() + " " + getLastName() + ", "
				+ getBirthDate();
	}
	
	public static String getSequenceName(){
		return PERSON_SEQUENCE;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
