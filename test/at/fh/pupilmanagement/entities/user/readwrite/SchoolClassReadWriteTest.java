package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;
import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassReadWriteTest extends AbstractReadWriteTest<SchoolClass>
{
	private static SchoolClassRepository lowerPermissionRepository = new SchoolClassRepository(lowerPermissionUser);
	private static SchoolClassRepository adminPermissionRepository = new SchoolClassRepository(adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(SchoolClass.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<SchoolClass> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
	}

	@Override
	protected BaseRepository<SchoolClass> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
	}
	
	@Override
	protected SchoolClass getTemplateEntity()
	{
		return new SchoolClass("BHMI",2);
	}
	
	@Override
	protected void modifyEntity(SchoolClass entity)
	{
		entity.setGrade(4);;
	}
}
