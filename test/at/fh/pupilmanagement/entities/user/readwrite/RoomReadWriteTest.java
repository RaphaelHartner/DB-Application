package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.RoomType;

public class RoomReadWriteTest extends AbstractReadWriteTest<Room>
{
	private static RoomRepository lowerPermissionRepository = new RoomRepository(lowerPermissionUser);
	private static RoomRepository adminPermissionRepository = new RoomRepository(adminPermissionUser);
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
