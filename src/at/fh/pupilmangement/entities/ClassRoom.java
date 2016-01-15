package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "class_room")
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
