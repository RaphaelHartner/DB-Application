package at.fh.pupilmangement.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pupil")
@DiscriminatorValue(value="pupil")
public class Pupil extends Person{

	private short yearOfEntry;
	@ManyToOne
	private SchoolClass schoolClass;
	
	public Pupil(){}
	
	public Pupil(String firstName, String lastName, Date birthDate, short yearOfEntry){
		super(firstName, lastName, birthDate);
		setYearOfEntry(yearOfEntry);
	}
	
	public Pupil(String firstName, String lastName, Date birthDate, short yearOfEntry, SchoolClass schoolClass){
		this(firstName, lastName, birthDate, yearOfEntry);
		setSchoolClass(schoolClass);
	}
	
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		if(schoolClass ==null)
			throw new IllegalArgumentException("ERROR: CurrentClass couldn't be null!");
		
		this.schoolClass = schoolClass;
		this.schoolClass.addPupil(this);
	}
	
	public short getYearOfEntry() {
		return yearOfEntry;
	}
	public void setYearOfEntry(short yearOfEntry) {
		this.yearOfEntry = yearOfEntry;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", " + getYearOfEntry();
	}
}
