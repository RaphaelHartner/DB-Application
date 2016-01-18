package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.Room;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;

public class RoomReadOnlyTest extends AbstractReadOnlyTest<Room>
{

	private static RoomRepository readerPermissionRepository = new RoomRepository(readerPermissionUser);
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
		readerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<Room> getLowerPermissionRepository()
	{
		return readerPermissionRepository;
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
