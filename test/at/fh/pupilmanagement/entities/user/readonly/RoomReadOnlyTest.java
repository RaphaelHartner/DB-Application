package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.RoomType;

public class RoomReadOnlyTest extends AbstractReadOnlyTest<Room>
{

	private static BaseRepository<Room> lowerPermissionRepository = new BaseRepository<Room>(Room.class,lowerPermissionUser);
	private static BaseRepository<Room> adminPermissionRepository = new BaseRepository<Room>(Room.class,adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Room.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Room.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<Room> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
	}

	@Override
	protected BaseRepository<Room> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
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
}
