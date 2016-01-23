package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.ClassRoom;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.ClassRoomRepository;

public class ClassRoomReadWriteTest extends AbstractReadWriteTest<ClassRoom>
{
	private static ClassRoomRepository writerPermissionRepository = new ClassRoomRepository(writerPermissionUser);
	private static ClassRoomRepository adminPermissionRepository = new ClassRoomRepository(adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(ClassRoom.getSequenceName(), lastTableId);
		writerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<ClassRoom> getWriterPermissionRepository()
	{
		return writerPermissionRepository;
	}

	@Override
	protected BaseRepository<ClassRoom> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
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
}
