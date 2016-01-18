package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;

public class SchoolClassReadOnlyTest extends AbstractReadOnlyTest<SchoolClass>
{
	private static SchoolClassRepository readerPermissionRepository = new SchoolClassRepository(readerPermissionUser);
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
		readerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<SchoolClass> getReaderPermissionRepository()
	{
		return readerPermissionRepository;
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
