package at.fh.pupilmangement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="Room")
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int maxPupils;
	private String position;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String toString(){
		return "Room : " + getName() + ", " + getPosition();
	}
}
