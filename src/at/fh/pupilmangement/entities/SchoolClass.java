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
public class SchoolClass extends BaseEntity
{
	private static final String SCHOOLCLASS_SEQUENCE = "schoolclass_sequence";

	@SequenceGenerator(name = "ClassIdGenerator", sequenceName = SCHOOLCLASS_SEQUENCE, allocationSize = 1)
	@Id
	@GeneratedValue(generator = "ClassIdGenerator")
	private long id;
	private String name;
	private int grade;

	@OneToOne
	private Teacher classTeacher;
	@OneToOne
	private ClassRoom classRoom;

	@OneToMany(mappedBy = "schoolClass")
	private List<Pupil> pupils = new ArrayList<Pupil>();

	@ManyToMany
	private List<Teacher> teachers = new ArrayList<Teacher>();
	@ManyToMany
	private List<Room> rooms = new ArrayList<Room>();

	public SchoolClass()
	{
	}

	public SchoolClass(String name, int grade)
	{
		setName(name);
		setGrade(grade);
	}

	public SchoolClass(String name, int grade, Teacher mainClassTeacher, ClassRoom mainClassRoom)
	{
		setName(name);
		setGrade(grade);
		setClassTeacher(mainClassTeacher);
		setClassRoom(mainClassRoom);
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public ClassRoom getClassRoom()
	{
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom)
	{
		this.classRoom = classRoom;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}

	public Teacher getClassTeacher()
	{
		return classTeacher;
	}

	public void setClassTeacher(Teacher classTeacher)
	{
		this.classTeacher = classTeacher;
		classTeacher.setMainClass(this);
	}

	public List<Teacher> getTeachers()
	{
		return teachers;
	}

	public void addTeacher(Teacher teacher)
	{
		if (teacher == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to teachers!");

		teachers.add(teacher);
		teacher.addSchoolClass(this);
	}

	public List<Room> getRooms()
	{
		return rooms;
	}
	
	public void addRoom(Room room)
	{
		if (room == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to rooms!");

		rooms.add(room);
		room.addSchoolClasses(this);
	}

	public List<Pupil> getPupils()
	{
		return pupils;
	}

	void addPupil(Pupil pupil)
	{

		if (pupil == null)
			throw new IllegalArgumentException("ERROR: Couldn't add NULL to pupils!");
		pupils.add(pupil);
	}

	@Override
	public String toString()
	{
		return "Class: " + getName() + ", " + getGrade();
	}

	public static String getSequenceName()
	{
		return SCHOOLCLASS_SEQUENCE;
	}
}
