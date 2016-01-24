package at.fh.pupilmanagement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "class_room")
@NamedQueries({
	@NamedQuery(name="SchoolClass.findSchoolClassesWithClassRoomInBuilding",
			query="SELECT s FROM SchoolClass s " +
				"JOIN s.classRoom r " +
				"WHERE r.position LIKE CONCAT(:building, '.%')"),
	@NamedQuery(name="Pupil.findAllPupils",
				query="SELECT p FROM Pupil p " +
					"JOIN p.schoolClass s " +
					"JOIN s.classRoom r " +
					"WHERE r.id = :id")
})
public class ClassRoom extends Room
{
	@OneToOne(mappedBy = "classRoom")
	public SchoolClass mainClass;
	
	public ClassRoom() {}
	
	public ClassRoom(int maxPupils, String position, RoomType roomType){
		super(maxPupils, position, roomType);
	}
	
	public SchoolClass getMainClass() {
		return mainClass;
	}

	public void setMainClass(SchoolClass schoolClass) {
		this.mainClass = schoolClass;
		if (schoolClass != null)
		{
			schoolClass.setClassRoom(this);
		}
	}
	
	@Override
	public BaseEntity createClonedEntity() {
		return new ClassRoom(getMaxPupils(),getPosition(),getRoomType());
	}
	
	@Override
	public String toString()
	{
		return "Classroom: " + getPosition() + ", " + getMaxPupils();
	}
}
