package at.fh.pupilmanagement.entities.user.readonly;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.BaseEntity;

public abstract class AbstractReadOnlyTest<T extends BaseEntity>
{
	protected final static User lowerPermissionUser = new User("pupilmanagement_reader", "pupilmanagement_reader");
	protected final static User adminPermissionUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> lowerPermissionRepository;
	protected BaseRepository<T> adminPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;
	
	@Before
	public void setup()
	{
		lowerPermissionRepository = getLowerPermissionRepository();
		adminPermissionRepository = getAdminPermissionRepository();
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
	
	protected abstract BaseRepository<T> getLowerPermissionRepository();
	protected abstract BaseRepository<T> getAdminPermissionRepository();
	protected abstract T getTemplateEntity();
	protected abstract void modifyEntity(T entity);
}
