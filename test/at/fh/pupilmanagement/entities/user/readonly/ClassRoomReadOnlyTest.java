package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.ClassRoom;
import at.fh.pupilmangement.entities.RoomType;

public class ClassRoomReadOnlyTest extends AbstractReadOnlyTest<ClassRoom>
{
	private static BaseRepository<ClassRoom> lowerPermissionRepository = new BaseRepository<ClassRoom>(ClassRoom.class,lowerPermissionUser);
	private static BaseRepository<ClassRoom> adminPermissionRepository = new BaseRepository<ClassRoom>(ClassRoom.class,adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(ClassRoom.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<ClassRoom> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
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
