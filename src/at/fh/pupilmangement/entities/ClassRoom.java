package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "class_room")
public class ClassRoom extends Room
{
	public ClassRoom() {}
	
	public ClassRoom(long id, int maxPupils, String position, RoomType roomType){
		super(id, maxPupils, position, roomType);
	}
	
	@Override
	public String toString()
	{
		return "Classroom: " + getPosition() + ", " + getMaxPupils();
	}
}
