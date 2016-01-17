package at.fh.pupilmanagement.relationships;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.RoomType;
import at.fh.pupilmangement.entities.SchoolClass;

public class RoomSchoolClassTest
{
	private static RoomRepository roomRepository;
	private Room currentTestRoom;
	private static long lastRoomTableId;
	
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		roomRepository = new RoomRepository();
		lastRoomTableId = RoomRepository.getLastTableId(Room.getSequenceName());
		
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestRoom = new Room(50, "C.0.01", RoomType.Turnsaal);
		roomRepository.saveToDb(currentTestRoom);
		
		currentTestSchoolClass = new SchoolClass("A", 2);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
	}
	
	@Test
	public void TestConnectSchoolClassToRoom()
	{
		SchoolClass anotherTestSchoolClass = new SchoolClass("C", 3);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		SchoolClass thirdTestSchoolClass = new SchoolClass("B", 3);
		schoolClassRepository.saveToDb(thirdTestSchoolClass);
		
		Room anotherTestRoom = new Room(15, "B.2.02", RoomType.Computerraum);
		roomRepository.saveToDb(anotherTestRoom);
		
		currentTestSchoolClass.addRoom(currentTestRoom);
		currentTestSchoolClass.addRoom(anotherTestRoom);
		anotherTestSchoolClass.addRoom(currentTestRoom);
		anotherTestSchoolClass.addRoom(anotherTestRoom);
		thirdTestSchoolClass.addRoom(currentTestRoom);
		thirdTestSchoolClass.addRoom(anotherTestRoom);
		
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		schoolClassRepository.saveToDb(thirdTestSchoolClass);
		
		Collection<Room> rooms = new ArrayList<Room>();
		rooms.add(currentTestRoom);
		rooms.add(anotherTestRoom);
		
		Collection<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
		schoolClasses.add(currentTestSchoolClass);
		schoolClasses.add(anotherTestSchoolClass);
		schoolClasses.add(thirdTestSchoolClass);
		
		Assert.assertEquals(rooms, currentTestSchoolClass.getRooms());
		Assert.assertEquals(rooms, anotherTestSchoolClass.getRooms());
		Assert.assertEquals(rooms, thirdTestSchoolClass.getRooms());
		
		Assert.assertEquals(schoolClasses, currentTestRoom.getSchoolClasses());
		Assert.assertEquals(schoolClasses, anotherTestRoom.getSchoolClasses());
		
		roomRepository.deleteFromDb(anotherTestRoom);
		schoolClassRepository.deleteFromDb(anotherTestSchoolClass);
		schoolClassRepository.deleteFromDb(thirdTestSchoolClass);
	}
	
	@Test
	public void TestConnectRoomToSchoolClass()
	{
		SchoolClass anotherTestSchoolClass = new SchoolClass("C", 3);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		Room anotherTestRoom = new Room(15, "B.2.02", RoomType.Computerraum);
		roomRepository.saveToDb(anotherTestRoom);
		
		Room thirdTestRoom = new Room(32, "A.1.11", RoomType.Physikraum);
		roomRepository.saveToDb(thirdTestRoom);
		
		currentTestRoom.addSchoolClasses(currentTestSchoolClass);
		currentTestRoom.addSchoolClasses(anotherTestSchoolClass);
		anotherTestRoom.addSchoolClasses(currentTestSchoolClass);
		anotherTestRoom.addSchoolClasses(anotherTestSchoolClass);
		thirdTestRoom.addSchoolClasses(currentTestSchoolClass);
		thirdTestRoom.addSchoolClasses(anotherTestSchoolClass);
		
		roomRepository.saveToDb(currentTestRoom);
		roomRepository.saveToDb(anotherTestRoom);
		roomRepository.saveToDb(thirdTestRoom);
		
		Collection<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
		schoolClasses.add(currentTestSchoolClass);
		schoolClasses.add(anotherTestSchoolClass);
		
		Assert.assertEquals(schoolClasses, currentTestRoom.getSchoolClasses());
		Assert.assertEquals(schoolClasses, anotherTestRoom.getSchoolClasses());
		Assert.assertEquals(schoolClasses, thirdTestRoom.getSchoolClasses());
		
		roomRepository.deleteFromDb(anotherTestRoom);
		roomRepository.deleteFromDb(thirdTestRoom);
		schoolClassRepository.deleteFromDb(anotherTestSchoolClass);
	}
	
	@After
	public void tearDown()
	{
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		roomRepository.rollbackInsertedData(Room.getSequenceName(), lastRoomTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		schoolClassRepository.closeConnetion();
		roomRepository.closeConnetion();
	}
}

