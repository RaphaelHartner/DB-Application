package at.fh.pupilmanagement.entities;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.queries.ReadAllQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmangement.entities.Room;

public class RoomTest {
	final Long ID = 1l;

	@Before
	public void setup() {

	}

	@Test
	public void testFind() {

		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Room room = entitymanager.find(Room.class, ID);

		Assert.assertNotNull(room);
	}

	@Test
	public void testUpdate() {

		final String room_name = "Klassenzimmer ";
		final String room_position = "A.1.";

		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Room room = entitymanager.find(Room.class, ID);

		Random random = new Random();
		int changedNumber = random.nextInt(100);

		room.setMaxPupils(changedNumber);
		room.setName(room_name + changedNumber);
		room.setPosition(room_position + changedNumber);

		entitymanager.getTransaction().commit();

		room = entitymanager.find(Room.class, ID);
		Assert.assertNotNull(room);
		Assert.assertEquals(room_name + changedNumber, room.getName());
		Assert.assertEquals(room_position + changedNumber, room.getPosition());
		Assert.assertEquals(changedNumber, changedNumber);

		entitymanager.close();
		emfactory.close();
	}

	@Test
	public void testAdd() {
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("PupilManagement");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Room newRoom = new Room();
		newRoom.setMaxPupils(25);
		newRoom.setName("TestName");
		newRoom.setPosition("TestPosition");

		entitymanager.persist(newRoom);
		entitymanager.getTransaction().commit();
		
		
		
		entitymanager.close();
		emfactory.close();

	}
}
