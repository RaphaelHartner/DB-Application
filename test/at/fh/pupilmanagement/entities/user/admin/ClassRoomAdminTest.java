package at.fh.pupilmanagement.entities.user.admin;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.ClassRoomRepository;
import at.fh.pupilmangement.entities.ClassRoom;
import at.fh.pupilmangement.entities.RoomType;

public class ClassRoomAdminTest extends AbstractAdminTest<ClassRoom>
{
	private static ClassRoomRepository adminPermissionRepository = new ClassRoomRepository(adminUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(ClassRoom.getSequenceName(), lastTableId);
		adminPermissionRepository.closeConnetion();
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
