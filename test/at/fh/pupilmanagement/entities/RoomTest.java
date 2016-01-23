package at.fh.pupilmanagement.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.Room;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;

public class RoomTest
{
	private static RoomRepository roomRepository;
	private Room currentTestRoom;
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		roomRepository = new RoomRepository();
		lastTableId = BaseRepository.getLastTableId(Room.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestRoom = new Room(30, "A.1.03", RoomType.Klassenzimmer);
		roomRepository.saveToDb(currentTestRoom);
	}
	
	@Test
	public void testRoomFind()
	{
		Assert.assertNotNull(roomRepository.find(currentTestRoom.getId()));
	}
	
	@Test
	public void testRoomInsert()
	{
		Room insertedRoom = roomRepository.find(currentTestRoom.getId());
		Assert.assertNotNull(insertedRoom);
	}
	
	@Test
	public void testRoomUpdate()
	{
		Room insertedRoom = roomRepository.find(currentTestRoom.getId());
		insertedRoom.setMaxPupils(20);
		insertedRoom.setPosition("B.2.01");
		insertedRoom.setRoomType(RoomType.Turnsaal);
		roomRepository.commit();
		Room updatedRoom = roomRepository.find(currentTestRoom.getId());		
		Assert.assertEquals(20, updatedRoom.getMaxPupils());
		Assert.assertEquals("B.2.01", updatedRoom.getPosition());
		Assert.assertEquals(RoomType.Turnsaal, updatedRoom.getRoomType());
	}
	
	@Test
	public void testRoomDelete()
	{
		Room insertedRoom = roomRepository.find(currentTestRoom.getId());
		Assert.assertNotNull(insertedRoom);
		
		roomRepository.deleteFromDb(insertedRoom);
		currentTestRoom = roomRepository.find(currentTestRoom.getId());
		Assert.assertNull(currentTestRoom);
	}
	
	@After
	public void teardown()
	{
		roomRepository.rollbackInsertedData(Room.getSequenceName(), lastTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		roomRepository.closeConnetion();
	}
}
