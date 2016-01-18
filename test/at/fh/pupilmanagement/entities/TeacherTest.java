package at.fh.pupilmanagement.entities;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.Teacher;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.TeacherRepository;

public class TeacherTest {

	private static TeacherRepository teacherRepository;
	private Teacher currentTestTeacher;
	private static long lastTableId;

	@BeforeClass
	public static void classSetup(){
		teacherRepository = new TeacherRepository();
		lastTableId = BaseRepository.getLastTableId(Teacher.getSequenceName());
	}
	
	@Before
	public void setup() {

		currentTestTeacher = new Teacher("Raphael", "Hartner",
				new GregorianCalendar(1994, 4, 23).getTime(), "HarR");
		teacherRepository.saveToDb(currentTestTeacher);
	}

	@Test
	public void testTeacherFind() {
		Assert.assertNotNull(teacherRepository.find(currentTestTeacher.getId()));
	}

	@Test
	public void testTeacherInsert() {

		Teacher insertedTeacher = teacherRepository
				.find(currentTestTeacher.getId());
		Assert.assertNotNull(insertedTeacher);
	}

	@Test
	public void testTeacherUpdate() {

		Teacher insertedTeacher = teacherRepository
				.find(currentTestTeacher.getId());
		insertedTeacher.setFirstName("Georg");
		insertedTeacher.setLastName("Adelmann");
		teacherRepository.commit();
		Teacher updatedTeacher = teacherRepository.find(currentTestTeacher.getId());

		Assert.assertEquals("Georg", updatedTeacher.getFirstName());
		Assert.assertEquals("Adelmann", updatedTeacher.getLastName());
	}

	@Test
	public void testTeacherDelete() {

		Teacher insertedTeacher = teacherRepository
				.find(currentTestTeacher.getId());
		Assert.assertNotNull(insertedTeacher);
		teacherRepository.deleteFromDb(insertedTeacher);

		currentTestTeacher = teacherRepository.find(currentTestTeacher.getId());
		Assert.assertNull(currentTestTeacher); // Should be deleted!
	}

	@AfterClass
	public static void classTeardown() {
		teacherRepository.rollbackInsertedData(Teacher.getSequenceName(),
				lastTableId);
		teacherRepository.closeConnetion();
	}

}
