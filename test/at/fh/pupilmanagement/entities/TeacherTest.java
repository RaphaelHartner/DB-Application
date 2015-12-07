package at.fh.pupilmanagement.entities;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmangement.entities.Teacher;

public class TeacherTest {

	EntityManagerFactory emFactory;
	EntityManager entityManager;

	@Before
	public void setup() {
		emFactory = Persistence.createEntityManagerFactory("PupilManagement");
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

//	@Test
//	public void testTeacher() {
//
//		at.fh.pupilmangement.entities.Person teacher = entityManager.find(
//				at.fh.pupilmangement.entities.Person.class, 2);
//		Assert.assertNotNull(teacher);
//
//	}

	@SuppressWarnings("deprecation")
	@Test
	public void testInsert() {

		try {
			Teacher teacher = new Teacher();
			teacher.setFirstName("Nico");
			teacher.setLastName("Mustermann");
			teacher.setBirthDate(new Date(1964, 11, 11)); //todo: use recommended Date
			teacher.setAbbreviation("MusN");

			entityManager.persist(teacher);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@After
	public void teardown() {
		entityManager.close();
		emFactory.close();
	}

}