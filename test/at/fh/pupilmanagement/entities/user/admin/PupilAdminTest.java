package at.fh.pupilmanagement.entities.user.admin;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;

public class PupilAdminTest extends AbstractAdminTest<Pupil>
{
	private static PupilRepository adminPermissionRepository = new PupilRepository(adminUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Pupil.getSequenceName(), lastTableId);
		adminPermissionRepository.closeConnetion();
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
