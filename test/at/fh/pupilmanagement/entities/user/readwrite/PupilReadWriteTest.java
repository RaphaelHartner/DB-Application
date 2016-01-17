package at.fh.pupilmanagement.entities.user.readwrite;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;
import at.fh.pupilmangement.entities.Pupil;

public class PupilReadWriteTest extends AbstractReadWriteTest<Pupil>
{
	private static PupilRepository lowerPermissionRepository = new PupilRepository(lowerPermissionUser);
	private static PupilRepository adminPermissionRepository = new PupilRepository(adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Pupil.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<Pupil> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
	}

	@Override
	protected BaseRepository<Pupil> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
	}
	
	@Override
	protected Pupil getTemplateEntity()
	{
		return new Pupil("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime(), (short)2012);
	}
	
	@Override
	protected void modifyEntity(Pupil entity)
	{
		entity.setYearOfEntry((short)2013);
	}
}
