package at.fh.pupilmanagement.entities.user.readwrite;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.fh.pupilmanagement.entities.Person;
import at.fh.pupilmanagement.repositories.BaseRepository;

public class PersonReadWriteTest extends AbstractReadWriteTest<Person>
{

	private static BaseRepository<Person> writerPermissionRepository = new BaseRepository<Person>(Person.class, writerPermissionUser);
	private static BaseRepository<Person> adminPermissionRepository = new BaseRepository<Person>(Person.class, adminPermissionUser);
	private static long lastTableId;

	@BeforeClass
	public static void classSetup()
	{
		lastTableId = BaseRepository.getLastTableId(Person.getSequenceName());
	}

	@AfterClass
	public static void classTeardown()
	{
		BaseRepository.setSequenceValue(Person.getSequenceName(), lastTableId);
		writerPermissionRepository.closeConnetion();
		adminPermissionRepository.closeConnetion();
	}

	@Override
	protected BaseRepository<Person> getWriterPermissionRepository()
	{
		return writerPermissionRepository;
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
