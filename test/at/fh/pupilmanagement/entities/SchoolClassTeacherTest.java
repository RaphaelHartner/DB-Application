package at.fh.pupilmanagement.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmangement.entities.SchoolClass;
import at.fh.pupilmangement.entities.Teacher;

public class SchoolClassTeacherTest {

	EntityManagerFactory emFactory;
	EntityManager entityManager;

	@Before
	public void setup() {
		emFactory = Persistence.createEntityManagerFactory("PupilManagement");
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Test
	public void testUpdate() {

		try {
			Teacher teacher = entityManager.find(Teacher.class, 1);
			SchoolClass schooClass = entityManager.find(SchoolClass.class, 1);

			schooClass.addTeacher(teacher);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Test
	public void testAddClassTeacher(){
		
		try {
			Teacher teacher = entityManager.find(Teacher.class, 1);
			SchoolClass schooClass = entityManager.find(SchoolClass.class, 1);

			schooClass.setClassTeacher(teacher);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@After
	public void teardown() {
		entityManager.close();
		emFactory.close();
	}

}
