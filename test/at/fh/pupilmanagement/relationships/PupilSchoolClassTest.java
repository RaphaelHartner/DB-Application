package at.fh.pupilmanagement.relationships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

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

public class PupilSchoolClassTest
{
	private static PupilRepository pupilRepository;
	private Pupil currentTestPupil;
	private static long lastPupilTableId;

	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;

	@BeforeClass
	public static void classSetup()
	{
		pupilRepository = new PupilRepository();
		lastPupilTableId = BaseRepository.getLastTableId(Pupil.getSequenceName());

		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}

	@Before
	public void setup()
	{
		currentTestPupil = new Pupil("Raphael", "Hartner", new GregorianCalendar(1994, 23, 4).getTime(), (short)2015);
		pupilRepository.saveToDb(currentTestPupil);

		currentTestSchoolClass = new SchoolClass("A", 2);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
	}


	@Test
	public void TestConnectPupilToSchoolClass()
	{
		Pupil anotherTestPupil = new Pupil("Georg", "Adelmann", new GregorianCalendar(1994, 05, 06).getTime(), (short)2015);
		pupilRepository.saveToDb(anotherTestPupil);

		currentTestSchoolClass.addPupil(currentTestPupil);
		currentTestSchoolClass.addPupil(anotherTestPupil);

		schoolClassRepository.saveToDb(currentTestSchoolClass);

		Collection<Pupil> pupils = new ArrayList<Pupil>();
		pupils.add(currentTestPupil);
		pupils.add(anotherTestPupil);

		Assert.assertEquals(pupils, currentTestSchoolClass.getPupils());

		pupilRepository.deleteFromDb(anotherTestPupil);
	}
	
	@Test
	public void TestConnectSchoolClassToPupil()
	{
		Pupil anotherTestPupil = new Pupil("Georg", "Adelmann", new GregorianCalendar(1994, 05, 06).getTime(), (short)2015);
		pupilRepository.saveToDb(anotherTestPupil);

		currentTestPupil.setSchoolClass(currentTestSchoolClass);
		pupilRepository.saveToDb(currentTestPupil);
		
		anotherTestPupil.setSchoolClass(currentTestSchoolClass);
		pupilRepository.saveToDb(anotherTestPupil);

		Collection<Pupil> pupils = new ArrayList<Pupil>();
		pupils.add(currentTestPupil);
		pupils.add(anotherTestPupil);

		Assert.assertEquals(pupils, currentTestSchoolClass.getPupils());

		pupilRepository.deleteFromDb(anotherTestPupil);
		pupilRepository.deleteFromDb(currentTestPupil);
	}

	@After
	public void tearDown()
	{
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(), lastPupilTableId);
	}

	@AfterClass
	public static void classTeardown()
	{
		schoolClassRepository.closeConnetion();
		pupilRepository.closeConnetion();
	}
}
