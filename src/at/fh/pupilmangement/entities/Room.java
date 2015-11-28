package at.fh.pupilmangement.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="Room")
public class Room extends BaseEntity{
	
	private int maxPupils;
	private String position;
	
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
		return "Room: " + getPosition();
	}
}
