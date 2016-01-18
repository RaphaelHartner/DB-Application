package at.fh.pupilmanagement.repositories;

import at.fh.pupilmanagement.entities.ClassRoom;
import at.fh.pupilmanagement.models.User;

public class ClassRoomRepository extends BaseRepository<ClassRoom>
{
	public ClassRoomRepository()
	{
		super(ClassRoom.class);
	}
	
	public ClassRoomRepository(User user)
	{
		super(ClassRoom.class, user);
	}
	
	@Override
	public void deleteFromDb(ClassRoom classRoom)
	{
		classRoom.setMainClass(null);
		classRoom = getEntityManager().merge(classRoom);
		getEntityManager().remove(classRoom);
		commit();
	}
}
