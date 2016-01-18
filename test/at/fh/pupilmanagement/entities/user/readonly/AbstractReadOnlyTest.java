package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.entities.BaseEntity;
import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;

public abstract class AbstractReadOnlyTest<T extends BaseEntity>
{
	protected final static User readerPermissionUser = new User("pupilmanagement_reader", "pupilmanagement_reader");
	protected final static User adminPermissionUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> readerPermissionRepository;
	protected BaseRepository<T> adminPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;
	
	@Before
	public void setup()
	{
		readerPermissionRepository = getReaderPermissionRepository();
		adminPermissionRepository = getAdminPermissionRepository();
		templateEntity = getTemplateEntity();
	}
	
	@Test
	public void testFindOperation()
	{
		try
		{
			readerPermissionRepository.findAll();
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
			readerPermissionRepository.saveToDb((T)templateEntity.createClonedEntity());
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception -> an exception should occur
		}
	}

	@Test
	public void testUpdateOperation()
	{
		T insertedEntity = readerPermissionRepository.find(insertEntity().getId());
		modifyEntity(insertedEntity);
		
		try
		{
			readerPermissionRepository.commit();
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception
		}
	}

	@Test
	public void testDeleteOperation()
	{	
		T insertedEntity = readerPermissionRepository.find(insertEntity().getId());
		
		try
		{
			readerPermissionRepository.deleteFromDb(insertedEntity);			
			Assert.fail();
		} catch (Exception e)
		{
			// Silent exception
		}
		
	}
	
	@After
	public void teardown()
	{
		adminPermissionRepository.rollbackInsertedData();
	}
	
	private T insertEntity(){
		@SuppressWarnings("unchecked")
		T entity = (T)templateEntity.createClonedEntity();
		adminPermissionRepository.saveToDb(entity);
		return entity;
	}
	
	protected abstract BaseRepository<T> getReaderPermissionRepository();
	protected abstract BaseRepository<T> getAdminPermissionRepository();
	protected abstract T getTemplateEntity();
	protected abstract void modifyEntity(T entity);
}
