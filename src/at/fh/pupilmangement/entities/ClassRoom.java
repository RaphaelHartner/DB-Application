package at.fh.pupilmangement.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="class_room")
public class ClassRoom extends Room{

	@Override
	public String toString() {
		return "Classroom: " + getPosition();
	}
}
