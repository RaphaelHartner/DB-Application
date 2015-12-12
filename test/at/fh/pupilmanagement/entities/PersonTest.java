package at.fh.pupilmanagement.entities;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Person;

public class PersonTest {

	private static BaseRepository<Person> personRepository;
	private Person currentTestPerson;
	private static long lastTableId;

	@BeforeClass
	public static void classSetup(){
		personRepository = new BaseRepository<Person>(Person.class);
		lastTableId = BaseRepository.getLastTableId(Person.getSequenceName());
	}
	
	@Before
	public void setup() {

		currentTestPerson = new Person("Raphael", "Hartner",
				new GregorianCalendar(1994, 4, 23).getTime());
		personRepository.saveToDb(currentTestPerson);
	}

	@Test
	public void testPersonFind() {
		Assert.assertNotNull(personRepository.find(currentTestPerson.getId()));
	}

	@Test
	public void testPersonInsert() {

		Person insertedPerson = personRepository
				.find(currentTestPerson.getId());
		Assert.assertNotNull(insertedPerson);
	}

	@Test
	public void testPersonUpdate() {

		Person insertedPerson = personRepository
				.find(currentTestPerson.getId());
		insertedPerson.setFirstName("Georg");
		insertedPerson.setLastName("Adelmann");
		personRepository.commit();
		Person updatedPerson = personRepository.find(currentTestPerson.getId());

		Assert.assertEquals("Georg", updatedPerson.getFirstName());
		Assert.assertEquals("Adelmann", updatedPerson.getLastName());
	}

	@Test
	public void testPersonDelete() {

		Person insertedPerson = personRepository
				.find(currentTestPerson.getId());
		Assert.assertNotNull(insertedPerson);
		personRepository.deleteFromDb(insertedPerson);

		currentTestPerson = personRepository.find(currentTestPerson.getId());
		Assert.assertNull(currentTestPerson); // Should be deleted!
	}

	@AfterClass
	public static void classTeardown() {
		personRepository.rollbackInsertedData(Person.getSequenceName(),
				lastTableId);
		personRepository.closeConnetion();
	}

}
