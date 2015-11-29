package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pupil")
@DiscriminatorValue(value="pupil")
public class Pupil extends Person{

	private short yearOfEntry;
	
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
