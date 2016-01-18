package at.fh.pupilmanagement.entities;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.PupilRepository;

public class PupilTest {

	private static PupilRepository pupilRepository;
	private Pupil currentTestPupil;
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
	}

	@Test
	public void testPupilFind() {
		Assert.assertNotNull(pupilRepository.find(currentTestPupil.getId()));
	}

	@Test
	public void testPupilInsert() {

		Pupil insertedPupil = pupilRepository.find(currentTestPupil.getId());
		Assert.assertNotNull(insertedPupil);
	}

	@Test
	public void testPupilUpdate() {

		Pupil insertedPupil = pupilRepository.find(currentTestPupil.getId());
		insertedPupil.setFirstName("Georg");
		insertedPupil.setLastName("Adelmann");
		pupilRepository.commit();
		Pupil updatedPupil = pupilRepository.find(currentTestPupil.getId());

		Assert.assertEquals("Georg", updatedPupil.getFirstName());
		Assert.assertEquals("Adelmann", updatedPupil.getLastName());
	}

	@Test
	public void testPupilDelete() {

		Pupil insertedPupil = pupilRepository.find(currentTestPupil.getId());
		Assert.assertNotNull(insertedPupil);
		pupilRepository.deleteFromDb(insertedPupil);

		currentTestPupil = pupilRepository.find(currentTestPupil.getId());
		Assert.assertNull(currentTestPupil); // Should be deleted!
	}

	@AfterClass
	public static void classTeardown() {
		pupilRepository.rollbackInsertedData(Pupil.getSequenceName(),
				lastTableId);
		pupilRepository.closeConnetion();
	}
}
