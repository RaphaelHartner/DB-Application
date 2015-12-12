package at.fh.pupilmanagement.entities;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Person;
import at.fh.pupilmangement.entities.Teacher;

public class TeacherTest {

	EntityManagerFactory emFactory;
	EntityManager entityManager;
	BaseRepository<Teacher> teacherRepository;
	long lastTableId;

	@Before
	public void setup() {
		teacherRepository = new BaseRepository<Teacher>(Teacher.class);
		lastTableId = BaseRepository.getNextSequenceValue(Teacher
				.getSequenceName()) - 1;

	}

	@Test
	public void testTeacherDelete() {

		Teacher teacher = new Teacher("Raphael", "Teacher",
				new GregorianCalendar(1990, 5, 20).getTime(), "TeaR");
		teacherRepository.saveToDb(teacher);
		Teacher insertedTeacher = teacherRepository.find(teacher.getId());
		Assert.assertNotNull(insertedTeacher);
		teacherRepository.delete(insertedTeacher);

		teacher = teacherRepository.find(teacher.getId());
		Assert.assertNull(teacher); //Should be deleted!

	}

	@After
	public void teardown() {
		teacherRepository.rollbackInsertedData(Person.getSequenceName(),
				lastTableId);
		teacherRepository.closeConnetion();
	}

}
