package at.fh.pupilmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	@NamedQuery(name="Room.findSuitableRooms",
	query="SELECT r FROM Room r " +
		"WHERE r.maxPupils >= " +
		"(SELECT COUNT(p) FROM Pupil p " +
		"JOIN p.schoolClass s " +
		"WHERE s.id = :id)")
})
public class SchoolClass extends BaseEntity {

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

	public SchoolClass() {
	}

	public SchoolClass(String name, int grade) {
		setName(name);
		setGrade(grade);
	}

	public SchoolClass(String name, int grade, Teacher mainClassTeacher,
			ClassRoom mainClassRoom) {
		setName(name);
		setGrade(grade);
		setClassTeacher(mainClassTeacher);
		setClassRoom(mainClassRoom);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
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

	public Teacher getClassTeacher() {
		return classTeacher;
	}

	public void setClassTeacher(Teacher classTeacher) {
		this.classTeacher = classTeacher;
		if (classTeacher != null)
		{
			classTeacher.setMainClass(this);
		}
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void addTeacher(Teacher teacher) {
		if (teacher == null)
			throw new IllegalArgumentException(
					"ERROR: Couldn't add NULL to teachers!");

		teachers.add(teacher);
		teacher.addSchoolClass(this);
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void addRoom(Room room) {
		if (room == null)
			throw new IllegalArgumentException(
					"ERROR: Couldn't add NULL to rooms!");

		rooms.add(room);
		room.addSchoolClasses(this);
	}

	public List<Pupil> getPupils() {
		return pupils;
	}

	public void addPupil(Pupil pupil) {

		if (pupil == null)
			throw new IllegalArgumentException(
					"ERROR: Couldn't add NULL to pupils!");
		pupils.add(pupil);
	}

	@Override
	public BaseEntity createClonedEntity() {
		return new SchoolClass(getName(), getGrade());
	}

	@Override
	public String toString() {
		return "Class: " + getName() + ", " + getGrade();
	}

	public static String getSequenceName() {
		return SCHOOLCLASS_SEQUENCE;
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
		if (!(obj instanceof SchoolClass))
			return false;
		SchoolClass other = (SchoolClass) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
