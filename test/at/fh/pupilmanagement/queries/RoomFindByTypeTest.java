package at.fh.pupilmanagement.queries;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.RoomType;

public class RoomFindByTypeTest
{
	private static RoomRepository roomRepository;
	private Room currentTestRoom;
	private Room anotherTestRoom;
	private Room thirdTestRoom;
	private static long lastTableId;

	@BeforeClass
	public static void classSetup() {
		roomRepository = new RoomRepository();
		lastTableId = BaseRepository.getLastTableId(Room.getSequenceName());
	}

	@Before
	public void setup() {

		currentTestRoom = new Room(20, "A.3.01", RoomType.Chemielabor);
		roomRepository.saveToDb(currentTestRoom);
		anotherTestRoom = new Room(25, "A.3.02", RoomType.Werkstatt);
		roomRepository.saveToDb(anotherTestRoom);
		thirdTestRoom = new Room(20, "B.2.12", RoomType.Chemielabor);
		roomRepository.saveToDb(thirdTestRoom);
	}

	@Test
	public void testFindByType() 
	{
		List<Room> chemistryLabs = new ArrayList<Room>();
		chemistryLabs.add(currentTestRoom);
		chemistryLabs.add(thirdTestRoom);
		
		List<Room> workshops = new ArrayList<Room>();
		workshops.add(anotherTestRoom);
		
		Assert.assertEquals(chemistryLabs, roomRepository.findByType(RoomType.Chemielabor));
		Assert.assertEquals(workshops, roomRepository.findByType(RoomType.Werkstatt));
		Assert.assertEquals(new ArrayList<Room>(), roomRepository.findByType(RoomType.Klassenzimmer));
	}

	@After
	public void teardown() {
		roomRepository.rollbackInsertedData(Room.getSequenceName(),
				lastTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		roomRepository.closeConnetion();
	}
}

