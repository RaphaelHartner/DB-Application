package at.fh.pupilmanagement.relationships;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.ClassRoomRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;
import at.fh.pupilmangement.entities.ClassRoom;
import at.fh.pupilmangement.entities.RoomType;
import at.fh.pupilmangement.entities.SchoolClass;

public class ClassRoomSchoolClassTest
{
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;
	
	private static ClassRoomRepository classRoomRepository;
	private ClassRoom currentTestClassRoom;
	private static long lastClassRoomTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
		
		classRoomRepository = new ClassRoomRepository();
		lastClassRoomTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestSchoolClass = new SchoolClass("A", 1);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		
		currentTestClassRoom = new ClassRoom(30, "B.1.05", RoomType.Klassenzimmer);
		classRoomRepository.saveToDb(currentTestClassRoom);
	}
	
	@Test
	public void TestConnectClassRoomToSchoolClass()
	{
		currentTestSchoolClass.setClassRoom(currentTestClassRoom);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		Assert.assertEquals(currentTestClassRoom, currentTestSchoolClass.getClassRoom());
	}
	
	@Test
	public void TestConnectSchoolClassToClassRoom()
	{
		currentTestClassRoom.setMainClass(currentTestSchoolClass);
		classRoomRepository.saveToDb(currentTestClassRoom);
		Assert.assertEquals(currentTestClassRoom, currentTestSchoolClass.getClassRoom());
		Assert.assertEquals(currentTestSchoolClass, currentTestClassRoom.getMainClass());
	}
	
	@Test(expected = Exception.class)
	public void TestConnectTwo() 
	{
		currentTestSchoolClass.setClassRoom(currentTestClassRoom);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		
		SchoolClass anotherTestSchoolClass = new SchoolClass("B", 2);		
		anotherTestSchoolClass.setClassRoom(currentTestClassRoom);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		schoolClassRepository.deleteFromDb(anotherTestSchoolClass);
	}
	
	@After
	public void tearDown()
	{
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		classRoomRepository.rollbackInsertedData(ClassRoom.getSequenceName(), lastClassRoomTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		schoolClassRepository.closeConnetion();
		classRoomRepository.closeConnetion();
	}
}
