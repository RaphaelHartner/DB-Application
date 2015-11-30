package at.fh.pupilmanagement.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassTest {
	final Long ID = 1l;

	EntityManagerFactory emFactory;
	EntityManager entityManager;
	
	@Before
	public void setup(){
		emFactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	@Test
	public void testInsert() {

		try {
			
			SchoolClass schoolClass = new SchoolClass();
			schoolClass.setName("b");
			schoolClass.setGrade(2);

			entityManager.persist(schoolClass);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@After
	public void teardown(){
		
		if(entityManager != null)
			entityManager.close();
		if(emFactory != null)
			emFactory.close();
	}
}
