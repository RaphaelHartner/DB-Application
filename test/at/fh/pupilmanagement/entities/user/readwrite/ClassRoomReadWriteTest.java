package at.fh.pupilmanagement.entities.user.readwrite;

import at.fh.pupilmangement.entities.ClassRoom;
import at.fh.pupilmangement.entities.RoomType;

public class ClassRoomReadWriteTest extends AbstractReadWriteTest<ClassRoom>
{

	@Override
	protected Class<ClassRoom> getEntityClass()
	{
		return ClassRoom.class;
	}

	@Override
	protected ClassRoom getTemplateEntity()
	{
		return new ClassRoom(25, "A.1.11", RoomType.Klassenzimmer);
	}

	@Override
	protected void modifyEntity(ClassRoom entity)
	{
		entity.setMaxPupils(35);
	}

	@Override
	protected String getSequenceName()
	{
		return ClassRoom.getSequenceName();
	}

}
