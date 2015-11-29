package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
@DiscriminatorValue(value="teacher")
public class Teacher extends Person{

	private String abbreviation;
	
	public void setAbbreviation(String abbreviation){
		this.abbreviation = abbreviation;
	}
	public String getAbbreviation(){
		return abbreviation;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", " + getAbbreviation();
	}
}
