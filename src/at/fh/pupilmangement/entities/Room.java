package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "concrete_room_type")
@DiscriminatorValue(value = "room")
public class Room{

	@SequenceGenerator(name = "RoomIdGenerator", sequenceName = "Room_Sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "RoomIdGenerator")
	private int id;
	private int maxPupils;
	private String position;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMaxPupils() {
		return maxPupils;
	}
	public void setMaxPupils(int maxPupils) {
		this.maxPupils = maxPupils;
	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Room: " + getPosition() + ", " + getMaxPupils();
	}
}
