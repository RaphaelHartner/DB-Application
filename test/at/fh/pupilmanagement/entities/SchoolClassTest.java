package at.fh.pupilmanagement.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmanagement.repositories.SchoolClassRepository;

public class SchoolClassTest
{
	private static SchoolClassRepository schoolClassRepository;
	private SchoolClass currentTestSchoolClass;
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		schoolClassRepository = new SchoolClassRepository();
		lastTableId = BaseRepository.getLastTableId(SchoolClass.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestSchoolClass = new SchoolClass("A", 1);
		schoolClassRepository.saveToDb(currentTestSchoolClass);
	}
	
	@Test
	public void testSchoolClassFind()
	{
		Assert.assertNotNull(schoolClassRepository.find(currentTestSchoolClass.getId()));
	}
	
	@Test
	public void testSchoolClassInsert()
	{	SchoolClass newSchoolClass = new SchoolClass("C", 3);
		schoolClassRepository.saveToDb(newSchoolClass);
		SchoolClass insertedSchoolClass = schoolClassRepository.find(newSchoolClass.getId());
		Assert.assertNotNull(insertedSchoolClass);
		Assert.assertEquals(3, insertedSchoolClass.getGrade());
		Assert.assertEquals("C", insertedSchoolClass.getName());
	}
	
	@Test
	public void testSchoolClassUpdate()
	{
		SchoolClass insertedSchoolClass = schoolClassRepository.find(currentTestSchoolClass.getId());
		insertedSchoolClass.setGrade(2);
		insertedSchoolClass.setName("B");
		schoolClassRepository.commit();
		SchoolClass updatedSchoolClass = schoolClassRepository.find(currentTestSchoolClass.getId());		
		Assert.assertEquals(2, updatedSchoolClass.getGrade());
		Assert.assertEquals("B", updatedSchoolClass.getName());
		schoolClassRepository.deleteFromDb(updatedSchoolClass);
	}
	
	@Test
	public void testSchoolClassDelete()
	{
		SchoolClass insertedSchoolClass = schoolClassRepository.find(currentTestSchoolClass.getId());
		Assert.assertNotNull(insertedSchoolClass);
		
		schoolClassRepository.deleteFromDb(insertedSchoolClass);
		currentTestSchoolClass = schoolClassRepository.find(currentTestSchoolClass.getId());
		Assert.assertNull(currentTestSchoolClass);
	}
	
	@After
	public void tearDown()
	{
		schoolClassRepository.rollbackInsertedData(SchoolClass.getSequenceName(), lastTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		schoolClassRepository.closeConnetion();
	}
}
