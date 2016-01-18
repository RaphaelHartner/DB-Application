package at.fh.pupilmanagement.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import at.fh.pupilmanagement.entities.Room;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.models.User;

public class RoomRepository extends BaseRepository<Room>
{
	public RoomRepository()
	{
		super(Room.class);
	}
	
	public RoomRepository(User user)
	{
		super(Room.class, user);
	}
	
	public List<Room> findByType(RoomType type)
	{
		TypedQuery<Room> query = getEntityManager().createNamedQuery(
				"Room.findRoomsByType", Room.class);
		query.setParameter("type", type);
		
		return query.getResultList();
	}
	
	@Override
	public void deleteFromDb(Room room)
	{
		for (SchoolClass schoolClass : room.getSchoolClasses())
		{
			schoolClass.getRooms().remove(room);
		}
		room = getEntityManager().merge(room);
		getEntityManager().remove(room);
		commit();
	}
}
