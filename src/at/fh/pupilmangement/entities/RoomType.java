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
}
