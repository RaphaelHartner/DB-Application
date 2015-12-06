package at.fh.pupilmangement.entities;

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
