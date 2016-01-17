package at.fh.pupilmanagement.entities.user.readonly;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Pupil;

public class PupilReadOnlyTest extends AbstractReadOnlyTest<Pupil>
{
	private static BaseRepository<Pupil> lowerPermissionRepository = new BaseRepository<Pupil>(Pupil.class,lowerPermissionUser);
	private static BaseRepository<Pupil> adminPermissionRepository = new BaseRepository<Pupil>(Pupil.class,adminPermissionUser);
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