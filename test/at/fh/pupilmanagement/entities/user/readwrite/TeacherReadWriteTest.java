package at.fh.pupilmanagement.entities.user.readwrite;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Teacher;

public class TeacherReadWriteTest extends AbstractReadWriteTest<Teacher>
{
	private static BaseRepository<Teacher> lowerPermissionRepository = new BaseRepository<Teacher>(Teacher.class,lowerPermissionUser);
	private static BaseRepository<Teacher> adminPermissionRepository = new BaseRepository<Teacher>(Teacher.class,adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Teacher.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Teacher.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}
	
	@Override
	protected BaseRepository<Teacher> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
	}

	@Override
	protected BaseRepository<Teacher> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
	}

	@Override
	protected Teacher getTemplateEntity()
	{
		return new Teacher("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime(), "HarR");
	}

	@Override
	protected void modifyEntity(Teacher entity)
	{
		entity.setAbbreviation("HartRap");
	}
}
