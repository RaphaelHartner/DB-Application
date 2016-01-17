package at.fh.pupilmanagement.repositories;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.SchoolClass;

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
