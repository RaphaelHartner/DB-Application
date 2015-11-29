package at.fh.pupilmangement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "RoomType")
public class RoomType {

	@SequenceGenerator(name = "RoomTypeIdGenerator", sequenceName = "RoomType_Sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "RoomTypeIdGenerator")
	private int id;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		return "RoomType: " + getType();
	}
}
