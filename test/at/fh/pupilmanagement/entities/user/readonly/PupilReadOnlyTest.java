package at.fh.pupilmanagement.entities.user.readonly;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;

public class PupilReadOnlyTest extends AbstractReadOnlyTest<Pupil>
{
	private static PupilRepository readerPermissionRepository = new PupilRepository(readerPermissionUser);
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
		readerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<Pupil> getReaderPermissionRepository()
	{
		return readerPermissionRepository;
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
