package at.fh.pupilmanagement.entities.user.readonly;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.Teacher;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.TeacherRepository;

public class TeacherReadOnlyTest extends AbstractReadOnlyTest<Teacher>
{
	private static TeacherRepository readerPermissionRepository = new TeacherRepository(readerPermissionUser);
	private static TeacherRepository adminPermissionRepository = new TeacherRepository(adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Teacher.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Teacher.getSequenceName(), lastTableId);
		readerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}
	
	@Override
	protected BaseRepository<Teacher> getLowerPermissionRepository()
	{
		return readerPermissionRepository;
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
