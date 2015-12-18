package at.fh.pupilmanagement.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.ClassRoom;
import at.fh.pupilmangement.entities.RoomType;

public class ClassRoomTest
{
	private static BaseRepository<ClassRoom> classRoomRepository;
	private ClassRoom currentTestClassRoom;
	private static long lastTableId;
	
	@BeforeClass
	public static void classSetup()
	{
		classRoomRepository = new BaseRepository<ClassRoom>(ClassRoom.class);
		lastTableId = BaseRepository.getLastTableId(ClassRoom.getSequenceName());
	}
	
	@Before
	public void setup()
	{
		currentTestClassRoom = new ClassRoom(25, "A.0.10", RoomType.Physikraum);
		classRoomRepository.saveToDb(currentTestClassRoom);
	}
	
	@Test
	public void testClassRoomFind()
	{
		Assert.assertNotNull(classRoomRepository.find(currentTestClassRoom.getId()));
	}
	
	@Test
	public void testClassRoomInsert()
	{
		ClassRoom insertedClassRoom = classRoomRepository.find(currentTestClassRoom.getId());
		Assert.assertNotNull(insertedClassRoom);
	}
	
	@Test
	public void testClassRoomUpdate()
	{
		ClassRoom insertedClassRoom = classRoomRepository.find(currentTestClassRoom.getId());
		insertedClassRoom.setMaxPupils(15);
		insertedClassRoom.setPosition("C.0.02");
		insertedClassRoom.setRoomType(RoomType.Werkstatt);
		classRoomRepository.commit();
		ClassRoom updatedClassRoom = classRoomRepository.find(currentTestClassRoom.getId());		
		Assert.assertEquals(15, updatedClassRoom.getMaxPupils());
		Assert.assertEquals("C.0.02", updatedClassRoom.getPosition());
		Assert.assertEquals(RoomType.Werkstatt, updatedClassRoom.getRoomType());
		classRoomRepository.deleteFromDb(updatedClassRoom);
	}
	
	@Test
	public void testClassRoomDelete()
	{
		ClassRoom insertedClassRoom = classRoomRepository.find(currentTestClassRoom.getId());
		Assert.assertNotNull(insertedClassRoom);
		
		classRoomRepository.deleteFromDb(insertedClassRoom);
		currentTestClassRoom = classRoomRepository.find(currentTestClassRoom.getId());
		Assert.assertNull(currentTestClassRoom);
	}
	
	@After
	public void teardown()
	{
		classRoomRepository.rollbackInsertedData(ClassRoom.getSequenceName(), lastTableId);
	}
	
	@AfterClass
	public static void classTeardown()
	{
		classRoomRepository.closeConnetion();
	}
}
