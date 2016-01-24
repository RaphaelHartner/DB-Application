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

import at.fh.pupilmanagement.entities.ClassRoom;
import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.entities.RoomType;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.ClassRoomRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;

public class ClassRoomFindAllPupilsTest
{
	private static ClassRoomRepository classRoomRepository;
	private ClassRoom currentTestClassRoom;
	private static long lastClassRoomTableId;
	
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;
	
	private static PupilRepository pupilRepository;
	private Pupil currentTestPupil;
	private Pupil anotherTestPupil;
	private static long lastPupilTableId;
	
	@BeforeClass
	public static void classSetup() {
		classRoomRepository = new ClassRoomRepository();
		lastClassRoomTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
		pupilRepository = new PupilRepository();
		lastPupilTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
	}
	
	@Before
	public void setup() {

		currentTestClassRoom = new ClassRoom(4, "A.3.01", RoomType.Klassenzimmer);
		classRoomRepository.saveToDb(currentTestClassRoom);
	
		currentTestSchoolClass = new SchoolClass("A", 2);
		currentTestSchoolClass.setClassRoom(currentTestClassRoom);
		schoolClassRepository.saveToDb(currentTestSchoolClass);	
		
		currentTestPupil = new Pupil("Georg", "Adelmann", new GregorianCalendar(1994,5,6).getTime(), (short)2015, currentTestSchoolClass);
		pupilRepository.saveToDb(currentTestPupil);
		
		anotherTestPupil = new Pupil("Raphael", "Hartner", new GregorianCalendar(1994,23,4).getTime(), (short)2015, currentTestSchoolClass);
		pupilRepository.saveToDb(anotherTestPupil);
	}
	
	@Test
	public void testFindAllPupils()
	{					
		List<Pupil> pupils = new ArrayList<Pupil>();
		pupils.add(currentTestPupil);
		pupils.add(anotherTestPupil);
		
		Assert.assertEquals(pupils, classRoomRepository.findAllPupils(currentTestClassRoom.getId()));
	}
	
	@After
	public void teardown() {
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		classRoomRepository.rollbackInsertedData(ClassRoom.getSequenceName(), lastClassRoomTableId);
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(), lastPupilTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		classRoomRepository.closeConnetion();
		schoolClassRepository.closeConnetion();
		pupilRepository.closeConnetion();
	}
}
