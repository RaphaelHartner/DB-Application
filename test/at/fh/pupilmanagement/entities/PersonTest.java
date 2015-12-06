package at.fh.pupilmanagement.entities;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.Repository;
import at.fh.pupilmangement.entities.Person;
import at.fh.pupilmangement.entities.Teacher;

public class PersonTest {

	EntityManagerFactory emFactory;
	EntityManager entityManager;
	Repository<Person> personRepository ;
	
	@Before
	public void setup(){
		emFactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
//		personRepository = new Repository<Person>(Person.class, Person.getSequenceName());
		
	}
	
	@Test
	public void testTeacher() {
		
		at.fh.pupilmangement.entities.Person teacher = entityManager.find(at.fh.pupilmangement.entities.Person.class, 2);
		Assert.assertNotNull(teacher);
		
	}
	
//	@SuppressWarnings("deprecation")
	@Test
	public void testTeacherInsert(){
		
		Teacher teacher = new Teacher();
//		teacher.setId(150);
		teacher.setFirstName("Nico");
		teacher.setLastName("Mustermann");
		teacher.setBirthDate(new GregorianCalendar(1994, 12, 25).getTime());
		
		
		entityManager.persist(teacher);
		entityManager.getTransaction().commit();
		
		long id = teacher.getId();
		Assert.assertEquals(150, id);
		
	}
	
	@After
	public void teardown(){
		entityManager.close();
		emFactory.close();
//		personRepository.resetTestData();
//		personRepository.closeConnetion();
	}

}
