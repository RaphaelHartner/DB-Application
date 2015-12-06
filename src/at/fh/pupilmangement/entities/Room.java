package at.fh.pupilmangement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "concrete_room_type")
@DiscriminatorValue(value = "room")
public class Room
{
	private static final String ROOM_SEQUENCE = "room_sequence";

	@SequenceGenerator(name = "RoomIdGenerator", sequenceName = ROOM_SEQUENCE, allocationSize = 1)
	@Id
	@GeneratedValue(generator = "RoomIdGenerator")
	private long id;
	private int maxPupils;
	private String position;
	
	@Enumerated(EnumType.STRING)
	private RoomType type;

	@ManyToMany
	private List<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getMaxPupils()
	{
		return maxPupils;
	}

	public void setMaxPupils(int maxPupils)
	{
		this.maxPupils = maxPupils;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}
	
	public RoomType getType()
	{
		return type;
	}

	public void setType(RoomType type)
	{
		this.type = type;
	}

	public void addSchoolClasses(SchoolClass schoolClass)
	{
		if (schoolClass == null)
			throw new IllegalArgumentException("ERROR: Can't add NULL to schoolClass");
		
		schoolClasses.add(schoolClass);
		schoolClass.addRoom(this);
	}

	@Override
	public String toString()
	{
		return "Room: " + getPosition() + ", " + getMaxPupils();
	}
	
	public static String getSequenceName(){
		return ROOM_SEQUENCE;
	}
}
