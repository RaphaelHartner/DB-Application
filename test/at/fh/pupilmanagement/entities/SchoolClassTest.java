package at.fh.pupilmanagement.entities;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.Person;
import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassTest
{
	BaseRepository<SchoolClass> schoolclassRepository;
	long lastTableId;
	
	@Before
	public void setup()
	{
		schoolclassRepository = new BaseRepository<SchoolClass>(SchoolClass.class);
		lastTableId = BaseRepository.getNextSequenceValue(Person.getSequenceName()) - 1;
		
		
	}

	@Test
	public void testInsert()
	{

	}

	@After
	public void teardown()
	{

	}
}
