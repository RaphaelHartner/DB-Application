package at.fh.pupilmanagement.entities;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Person;

public class PersonTest {

	EntityManagerFactory emFactory;
	EntityManager entityManager;
	BaseRepository<Person> personRepository;
	long lastTableId;

	@Before
	public void setup() {
		personRepository = new BaseRepository<Person>(Person.class);
		lastTableId = BaseRepository.getNextSequenceValue(Person
				.getSequenceName()) - 1;
	}

	@Test
	public void testTeacherInsert() {

		Person person = new Person();
		person.setId(lastTableId + 1);
		person.setFirstName("Raphael");
		person.setLastName("Hartner");
		person.setBirthDate(new GregorianCalendar(1994, 4, 24).getTime());

		personRepository.saveToDb(person);

		Person insertedPerson = personRepository.find(lastTableId + 1);
		Assert.assertNotNull(insertedPerson);
	}

	@After
	public void teardown() {
		personRepository.rollbackInsertedData(Person.getSequenceName(),
				lastTableId);
		personRepository.closeConnetion();
	}

}
