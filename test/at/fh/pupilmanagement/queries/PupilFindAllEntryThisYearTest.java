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

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;
import at.fh.pupilmangement.entities.Pupil;

public class PupilFindAllEntryThisYearTest
{
	private static PupilRepository pupilRepository;
	private Pupil currentTestPupil;
	private Pupil anotherTestPupil;
	private Pupil thirdTestPupil;
	private static long lastTableId;

	@BeforeClass
	public static void classSetup() {
		pupilRepository = new PupilRepository();
		lastTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());
	}

	@Before
	public void setup() {

		currentTestPupil = new Pupil("Raphael", "Hartner",
				new GregorianCalendar(1994, 4, 23).getTime(), (short) 2012);
		pupilRepository.saveToDb(currentTestPupil);
		anotherTestPupil = new Pupil("Georg", "Adelmann",
				new GregorianCalendar(1994, 5, 6).getTime(), (short) Calendar.getInstance().get(Calendar.YEAR));
		pupilRepository.saveToDb(anotherTestPupil);
		thirdTestPupil = new Pupil("Martha", "Fahker",
				new GregorianCalendar(1994, 12, 13).getTime(), (short) Calendar.getInstance().get(Calendar.YEAR));
		pupilRepository.saveToDb(thirdTestPupil);
	}

	@Test
	public void testFindAllEntryThisYear() 
	{
		List<Pupil> pupilsEntryThisYear = new ArrayList<>();
		pupilsEntryThisYear.add(anotherTestPupil);
		pupilsEntryThisYear.add(thirdTestPupil);
		
		Assert.assertEquals(pupilsEntryThisYear, pupilRepository.findAllEntryThisYear());
	}

	@After
	public void teardown() {
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(),
				lastTableId);
	}
	
	@AfterClass
	public static void classTeardown() {
		pupilRepository.closeConnetion();
	}

}