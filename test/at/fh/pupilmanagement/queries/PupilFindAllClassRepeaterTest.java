package at.fh.pupilmanagement.queries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;

public class PupilFindAllClassRepeaterTest
{
	private static PupilRepository pupilRepository;
	private static SchoolClassRepository schoolClassRepository;
	private List<Pupil> repeaterPupils;
	private SchoolClass currentSchoolClass;
	private static long lastPupilTableId;
	private static long lastSchoolClassTableId;

	@BeforeClass
	public static void classSetup() {
		pupilRepository = new PupilRepository();
		schoolClassRepository = new SchoolClassRepository();
		
		lastPupilTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}

	@Before
	public void setup() {

		repeaterPupils = new ArrayList<Pupil>();
		currentSchoolClass = new SchoolClass("BHMI", 3);
		
		schoolClassRepository.saveToDb(currentSchoolClass);
		
		Pupil firstFepeaterTestPupil = new Pupil("Raphael", "Hartner",
				new GregorianCalendar(1994, 4, 23).getTime(), (short) (Calendar.getInstance().get(Calendar.YEAR) - 4), currentSchoolClass);
		pupilRepository.saveToDb(firstFepeaterTestPupil);
		
		Pupil secondRepeaterTestPupil = new Pupil("Georg", "Adelmann",
				new GregorianCalendar(1994, 5, 6).getTime(), (short) (Calendar.getInstance().get(Calendar.YEAR) - 5), currentSchoolClass);
		pupilRepository.saveToDb(secondRepeaterTestPupil);
		
		repeaterPupils.add(firstFepeaterTestPupil);
		repeaterPupils.add(secondRepeaterTestPupil);
		
		Pupil normalTestPupil = new Pupil("Martha", "Fahker",
				new GregorianCalendar(1994, 12, 13).getTime(), (short) (Calendar.getInstance().get(Calendar.YEAR) - 3) 
				, currentSchoolClass);
		pupilRepository.saveToDb(normalTestPupil);
		
		
	}

	@Test
	public void testFindAllEntryThisYear() 
	{	
		Assert.assertEquals(repeaterPupils, pupilRepository.findAllClassRepeater());
	}

	@After
	public void teardown() {
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(),
				lastPupilTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		pupilRepository.closeConnetion();
	}

}