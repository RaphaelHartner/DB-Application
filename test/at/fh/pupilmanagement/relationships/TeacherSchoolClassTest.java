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

import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.entities.Teacher;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;
import at.fh.pupilmanagement.repositories.TeacherRepository;

public class TeacherSchoolClassTest
{
	private static TeacherRepository teacherRepository;
	private Teacher currentTestTeacher;
	private static long lastTeacherTableId;
	
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastSchoolClassTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		teacherRepository = new TeacherRepository();
		lastTeacherTableId = BaseRepository.getLastTableId(Teacher.getSequenceName());
		
		schoolClassRepository = new SchoolClassRepository();
		lastSchoolClassTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestTeacher = new Teacher("Udo", "Wasmachst", new GregorianCalendar(1966, 10, 23).getTime(), "WasU");
		teacherRepository.saveToDb(currentTestTeacher);
		
		currentTestSchoolClass = new SchoolClass("A", 2);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
	}
	
	@Test
	public void TestConnectTeacherToMainClass()
	{
		currentTestTeacher.setMainClass(currentTestSchoolClass);
		teacherRepository.saveToDb(currentTestTeacher);
		Assert.assertEquals(currentTestSchoolClass, currentTestTeacher.getMainClass());
	}
	
	@Test
	public void TestConnectSchoolClassToClassTeacher()
	{
		currentTestSchoolClass.setClassTeacher(currentTestTeacher);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		Assert.assertEquals(currentTestTeacher, currentTestSchoolClass.getClassTeacher());
		Assert.assertEquals(currentTestSchoolClass, currentTestTeacher.getMainClass());
	}
	
	@Test
	public void TestConnectSchoolClassToTeacher()
	{
		SchoolClass anotherTestSchoolClass = new SchoolClass("C", 3);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		SchoolClass thirdTestSchoolClass = new SchoolClass("B", 3);
		schoolClassRepository.saveToDb(thirdTestSchoolClass);
		
		Teacher anotherTestTeacher = new Teacher("Peter", "Moser", new GregorianCalendar(1970, 10, 10).getTime(), "MosP");
		teacherRepository.saveToDb(anotherTestTeacher);
		
		currentTestSchoolClass.addTeacher(currentTestTeacher);
		currentTestSchoolClass.addTeacher(anotherTestTeacher);
		anotherTestSchoolClass.addTeacher(currentTestTeacher);
		anotherTestSchoolClass.addTeacher(anotherTestTeacher);
		thirdTestSchoolClass.addTeacher(currentTestTeacher);
		thirdTestSchoolClass.addTeacher(anotherTestTeacher);		
		
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		schoolClassRepository.saveToDb(thirdTestSchoolClass);
		
		Collection<Teacher> teachers = new ArrayList<Teacher>();
		teachers.add(currentTestTeacher);
		teachers.add(anotherTestTeacher);
		
		Collection<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
		schoolClasses.add(currentTestSchoolClass);
		schoolClasses.add(anotherTestSchoolClass);
		schoolClasses.add(thirdTestSchoolClass);
		
		Assert.assertEquals(teachers, currentTestSchoolClass.getTeachers());
		Assert.assertEquals(teachers, anotherTestSchoolClass.getTeachers());
		Assert.assertEquals(teachers, thirdTestSchoolClass.getTeachers());
		
		Assert.assertEquals(schoolClasses, currentTestTeacher.getSchoolClasses());
		Assert.assertEquals(schoolClasses, anotherTestTeacher.getSchoolClasses());
		
		teacherRepository.deleteFromDb(anotherTestTeacher);
		schoolClassRepository.deleteFromDb(anotherTestSchoolClass);
		schoolClassRepository.deleteFromDb(thirdTestSchoolClass);
	}
	
	@Test(expected = Exception.class)
	public void TestConnectTwoClassTeachersToSchoolClass() 
	{
		currentTestSchoolClass.setClassTeacher(currentTestTeacher);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
		
		SchoolClass anotherTestSchoolClass = new SchoolClass("B", 2);		
		anotherTestSchoolClass.setClassTeacher(currentTestTeacher);
		schoolClassRepository.saveToDb(anotherTestSchoolClass);
		
		schoolClassRepository.deleteFromDb(anotherTestSchoolClass);
	}
	
	@After
	public void tearDown()
	{
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastSchoolClassTableId);
		teacherRepository.rollbackInsertedData(Teacher.getSequenceName(), lastTeacherTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		schoolClassRepository.closeConnetion();
		teacherRepository.closeConnetion();
	}
}

