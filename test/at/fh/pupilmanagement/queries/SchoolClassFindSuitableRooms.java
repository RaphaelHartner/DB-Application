package at.fh.pupilmanagement.queries;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;
import at.fh.pupilmanagement.repositories.RoomRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;
import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.entities.Room;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.entities.SchoolClass;

public class SchoolClassFindSuitableRooms
{
	private static RoomRepository roomRepository;
	private Room currentTestRoom;
	private Room anotherTestRoom;
	private Room thirdTestRoom;
	private static long lastRoomTableId;
	
	private static PupilRepository pupilRepository;
	private Pupil currentTestPupil;
	private Pupil anotherTestPupil;
	private Pupil thirdTestPupil;
	private static long lastPupilTableId;
	
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;

	@BeforeClass
	public static void classSetup() {
		roomRepository = new RoomRepository();
		lastRoomTableId = BaseRepository.getLastTableId(Room.getSequenceName());
		pupilRepository = new PupilRepository();
		lastPupilTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}

	@Before
	public void setup() {

		currentTestRoom = new Room(4, "A.3.01", RoomType.Chemielabor);
		roomRepository.saveToDb(currentTestRoom);
		anotherTestRoom = new Room(3, "A.3.02", RoomType.Werkstatt);
		roomRepository.saveToDb(anotherTestRoom);
		thirdTestRoom = new Room(2, "B.2.12", RoomType.Chemielabor);
		roomRepository.saveToDb(thirdTestRoom);
	
		currentTestSchoolClass = new SchoolClass("A", 2);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		
		currentTestPupil = new Pupil("Georg", "Adelmann", new GregorianCalendar(1994, 5, 6).getTime(), (short)2015, currentTestSchoolClass);
		pupilRepository.saveToDb(currentTestPupil);
		anotherTestPupil = new Pupil("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime(), (short)2015, currentTestSchoolClass);
		pupilRepository.saveToDb(anotherTestPupil);
		thirdTestPupil = new Pupil("Walter", "Ferge", new GregorianCalendar(1994, 12, 12).getTime(), (short)2015, currentTestSchoolClass);
		pupilRepository.saveToDb(thirdTestPupil);
	}

	@Test
	public void testFindSuitableRooms()
	{					
		List<Room> suitableRooms = new ArrayList<Room>();
		suitableRooms.add(currentTestRoom);
		suitableRooms.add(anotherTestRoom);

		Assert.assertEquals(suitableRooms, schoolClassRepository.findSuitableRooms(currentTestSchoolClass.getId()));
		
		schoolClassRepository.deleteFromDb(currentTestSchoolClass);
	}

	@After
	public void teardown() {
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(), lastPupilTableId);
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		roomRepository.rollbackInsertedData(Room.getSequenceName(), lastRoomTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		roomRepository.closeConnetion();
		schoolClassRepository.closeConnetion();
		pupilRepository.closeConnetion();
	}
}
