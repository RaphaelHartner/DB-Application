package at.fh.pupilmanagement.entities.user.readonly;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Person;

public class PersonReadOnlyTest extends AbstractReadOnlyTest<Person>
{
	
	private static BaseRepository<Person> lowerPermissionRepository = new BaseRepository<Person>(Person.class,lowerPermissionUser);
	private static BaseRepository<Person> adminPermissionRepository = new BaseRepository<Person>(Person.class,adminPermissionUser);
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup(){
		lastTableId = BaseRepository.getLastTableId(Person.getSequenceName());
	}
	
	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Person.getSequenceName(), lastTableId);
		lowerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}
	
	@Override
	protected BaseRepository<Person> getLowerPermissionRepository()
	{
		return lowerPermissionRepository;
	}

	@Override
	protected BaseRepository<Person> getAdminPermissionRepository()
	{
		return adminPermissionRepository;
	}
	
	@Override
	protected Person getTemplateEntity()
	{
		return new Person("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime());
	}
	
	@Override
	protected void modifyEntity(Person entity)
	{
		entity.setFirstName("Georg");
	}
}
