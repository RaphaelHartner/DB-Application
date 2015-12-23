package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.BaseEntity;
import at.fh.pupilmangement.entities.Person;

public abstract class AbstractReadOnlyTest<T extends BaseEntity>
{
	protected final static User readerUser = new User("pupilmanagement_reader", "pupilmanagement_reader");
	protected final static User adminUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> lowerPermissionRepository;
	protected BaseRepository<T> higherPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;
	
	
	@Before
	public void setup()
	{
		lowerPermissionRepository = new BaseRepository<T>(getEntityClass(), readerUser);
		higherPermissionRepository = new BaseRepository<T>(getEntityClass(), adminUser);
		lastTableId = BaseRepository.getLastTableId(Person.getSequenceName());
		templateEntity = getTemplateEntity();
	}
	
	@Test
	public void testFindOperation()
	{
		try
		{
			lowerPermissionRepository.findAll();
		} catch (Exception e)
		{
			Assert.fail();
		}
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void testInsertOperation()
	{
		try
		{
			lowerPermissionRepository.saveToDb((T)templateEntity.createClonedEntity());
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception -> an exception should occur
		}
	}

	@Test
	public void testUpdateOperation()
	{
		T insertedEntity = lowerPermissionRepository.find(insertEntity().getId());
		modifyEntity(insertedEntity);
		
		try
		{
			lowerPermissionRepository.commit();
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception
		}
	}

	@Test
	public void testDeleteOperation()
	{	
		T insertedEntity = lowerPermissionRepository.find(insertEntity().getId());
		
		try
		{
			lowerPermissionRepository.deleteFromDb(insertedEntity);			
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception
		}
		
	}

	@After
	public void classTeardown()
	{
		BaseRepository.setSequenceValue(getSequenceName(), lastTableId);
		lowerPermissionRepository.rollbackInsertedData();
		lowerPermissionRepository.closeConnetion();
		
		higherPermissionRepository.rollbackInsertedData();
		higherPermissionRepository.closeConnetion();
	}
	
	private T insertEntity(){
		@SuppressWarnings("unchecked")
		T entity = (T)templateEntity.createClonedEntity();
		higherPermissionRepository.saveToDb(entity);
		return entity;
	}
	
	protected abstract Class<T> getEntityClass();
	protected abstract T getTemplateEntity();
	protected abstract void modifyEntity(T entity);
	protected abstract String getSequenceName();
	
	
	
}
