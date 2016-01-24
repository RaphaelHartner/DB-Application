package at.fh.pupilmanagement.queries;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.ClassRoom;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.ClassRoomRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;

public class FindSchoolClassesWithClassRoomInBuildingTest
{
	private static ClassRoomRepository classRoomRepository;
	private ClassRoom currentTestClassRoom;
	private ClassRoom anotherTestClassRoom;
	private ClassRoom thirdTestClassRoom;
	private static long lastClassRoomTableId;
	
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private SchoolClass anotherTestSchoolClass;
	private SchoolClass thirdTestSchoolClass;
	private static long lastSchoolClassTableId;
	
	@BeforeClass
	public static void classSetup() {
		classRoomRepository = new ClassRoomRepository();
		lastClassRoomTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}
	
	@Before
	public void setup() {

		currentTestClassRoom = new ClassRoom(4, "A.3.01", RoomType.Klassenzimmer);
		classRoomRepository.saveToDb(currentTestClassRoom);
		anotherTestClassRoom = new ClassRoom(3, "A.3.02", RoomType.Klassenzimmer);
		classRoomRepository.saveToDb(anotherTestClassRoom);
		thirdTestClassRoom = new ClassRoom(2, "B.2.12", RoomType.Klassenzimmer);
		classRoomRepository.saveToDb(thirdTestClassRoom);
	
		currentTestSchoolClass = new SchoolClass("A", 2);
		currentTestSchoolClass.setClassRoom(currentTestClassRoom);
		schoolClassRepository.saveToDb(currentTestSchoolClass);	
		
		anotherTestSchoolClass = new SchoolClass("A", 1);
		anotherTestSchoolClass.setClassRoom(anotherTestClassRoom);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		
		thirdTestSchoolClass = new SchoolClass("C", 2);
		thirdTestSchoolClass.setClassRoom(thirdTestClassRoom);
		schoolClassRepository.saveToDb(thirdTestSchoolClass);
	}
	
	@Test
	public void testFindSchoolClassesWithClassRoomInBuilding()
	{					
		List<SchoolClass> schoolClassesInBuildingA = new ArrayList<SchoolClass>();
		schoolClassesInBuildingA.add(currentTestSchoolClass);
		schoolClassesInBuildingA.add(anotherTestSchoolClass);
		
		List<SchoolClass> schoolClassesInBuildingB = new ArrayList<SchoolClass>();
		schoolClassesInBuildingB.add(thirdTestSchoolClass);
		
		Assert.assertEquals(schoolClassesInBuildingA, classRoomRepository.findSchoolClassesWithClassRoomInBuilding("A"));
		Assert.assertEquals(schoolClassesInBuildingB, classRoomRepository.findSchoolClassesWithClassRoomInBuilding("B"));
		Assert.assertEquals(new ArrayList<SchoolClass>(), classRoomRepository.findSchoolClassesWithClassRoomInBuilding("C"));
	}
	
	@After
	public void teardown() {
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		classRoomRepository.rollbackInsertedData(ClassRoom.getSequenceName(), lastClassRoomTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		classRoomRepository.closeConnetion();
		schoolClassRepository.closeConnetion();
	}
}