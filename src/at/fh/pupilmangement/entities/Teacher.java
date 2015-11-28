package at.fh.pupilmangement.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
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
