package at.fh.pupilmangement.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="teacher")
public class Teacher extends Person{

	private String abbreviation;
	@OneToOne(mappedBy="classTeacher")
	private SchoolClass mainClass;
	@ManyToMany(mappedBy="teachers")
	private Collection<SchoolClass> schoolclasses = new ArrayList<SchoolClass>();
	
	public SchoolClass getMainClass() {
		return mainClass;
	}
	void setMainClass(SchoolClass mainClass) {
		this.mainClass = mainClass;
	}
	
	public Collection<SchoolClass> getSchoolClasses(){
		return schoolclasses;
	}
	void addSchoolClass(SchoolClass schoolClass){
		if(schoolClass == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to classes!");
		
		schoolclasses.add(schoolClass);
	}
	
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
