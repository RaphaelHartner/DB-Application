package at.fh.pupilmangement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class SchoolClass {

	@SequenceGenerator(name = "ClassIdGenerator", sequenceName = "SchoolClass_Sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "ClassIdGenerator")
	private int id;
	private String name;
	private int grade;
	
	@OneToOne
	private Teacher classTeacher; 
	@OneToMany(mappedBy="schoolClass")
	private List<Pupil> pupils = new ArrayList<Pupil>();
	@ManyToMany
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	public Teacher getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(Teacher classTeacher) {
		this.classTeacher = classTeacher;
		classTeacher.setMainClass(this);
	}
	
	public List<Teacher> getTeachers(){
		return teachers;
	}
	public void addTeacher(Teacher teacher){
		if(teacher == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to teachers!");
		
		teachers.add(teacher);
		teacher.addSchoolClass(this);
	}
	
	public List<Pupil> getPupils(){
		return pupils;
	}
	void addPupil(Pupil pupil){
		
		if(pupil == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to pupils!");
		pupils.add(pupil);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString(){
		return "Class: " +  getName() + ", " + getGrade();
	}
}
