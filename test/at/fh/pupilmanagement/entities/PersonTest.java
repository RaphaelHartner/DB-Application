package at.fh.pupilmanagement.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import at.fh.pupilmangement.entities.Teacher;

public class PersonTest {

	@Test
	public void testTeacher() {
		
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Teacher teacher = entitymanager.find(Teacher.class, 1l);

		Assert.assertNotNull(teacher);
		
		entitymanager.close();
		emfactory.close();
	}

}
