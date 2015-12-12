package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "class_room")
public class ClassRoom extends Room
{
	public ClassRoom() {}
	
	public ClassRoom(int maxPupils, String position, RoomType roomType){
		super(maxPupils, position, roomType);
	}
	
	@Override
	public String toString()
	{
		return "Classroom: " + getPosition() + ", " + getMaxPupils();
	}
}
