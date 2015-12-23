package at.fh.pupilmanagement.entities.user.readonly;

import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.RoomType;

public class RoomReadOnlyTest extends AbstractReadOnlyTest<Room>
{

	@Override
	protected Class<Room> getEntityClass()
	{
		return Room.class;
	}

	@Override
	protected Room getTemplateEntity()
	{
		return new Room(25, "A.1.11", RoomType.Klassenzimmer);
	}

	@Override
	protected void modifyEntity(Room entity)
	{
		entity.setMaxPupils(35);
	}

	@Override
	protected String getSequenceName()
	{
		return Room.getSequenceName();
	}

}
