package at.fh.pupilmanagement.entities;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmangement.entities.ClassRoom;

public class RoomTest {
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
	public void testFind() {

//		EntityManagerFactory emfactory = Persistence
//				.createEntityManagerFactory("PupilManagement");
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//
//		Room room = entitymanager.find(Room.class, ID);
//
//		Assert.assertNotNull(room);
	}

	@Test
	public void testUpdate() {

//		final String room_name = "Klassenzimmer ";
//		final String room_position = "A.1.";
//
//		EntityManagerFactory emfactory = Persistence
//				.createEntityManagerFactory("PupilManagement");
//		EntityManager entitymanager = emfactory.createEntityManager();
//		entitymanager.getTransaction().begin();
//
//		Room room = entitymanager.find(Room.class, ID);
//
//		Random random = new Random();
//		int changedNumber = random.nextInt(100);
//
//		room.setMaxPupils(changedNumber);
//		room.setName(room_name + changedNumber);
//		room.setPosition(room_position + changedNumber);
//
//		entitymanager.getTransaction().commit();
//
//		room = entitymanager.find(Room.class, ID);
//		Assert.assertNotNull(room);
//		Assert.assertEquals(room_name + changedNumber, room.getName());
//		Assert.assertEquals(room_position + changedNumber, room.getPosition());
//		Assert.assertEquals(changedNumber, changedNumber);
//
//		entitymanager.close();
//		emfactory.close();
	}

	@Test
	public void testAdd() {
		
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		ClassRoom newRoom = new ClassRoom();
		Random rnd = new Random();
		newRoom.setMaxPupils(rnd.nextInt(40));
//		newRoom.setName("TestName");
		newRoom.setPosition("A.1.01");

		entitymanager.persist(newRoom);
		entitymanager.getTransaction().commit();
	}
	
	@After
	public void teardown(){
		
		if(entityManager != null)
			entityManager.close();
		if(emFactory != null)
			emFactory.close();
	}
}
