package at.fh.pupilmanagement.entities.user.admin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.entities.BaseEntity;
import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;

public abstract class AbstractAdminTest<T extends BaseEntity>
{
	protected final static User adminUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> adminPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;

	@Before
	public void setup()
	{
		adminPermissionRepository = getAdminPermissionRepository();
		templateEntity = getTemplateEntity();
	}

	@Test
	public void testFindOperation()
	{
		try
		{
			adminPermissionRepository.findAll();
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
			adminPermissionRepository.saveToDb((T) templateEntity.createClonedEntity());
		} catch (Exception e)
		{
			Assert.fail();
		}
	}

	@Test
	public void testUpdateOperation()
	{
		modifyEntity(insertEntity());

		try
		{
			adminPermissionRepository.commit();
		} catch (Exception e)
		{
			Assert.fail();
		}
	}

	@Test
	public void testDeleteOperation()
	{
		T insertedEntity = insertEntity();
		try
		{
			adminPermissionRepository.deleteFromDb(insertedEntity);
		} catch (Exception e)
		{
			Assert.fail();
		}

	}
	
	@After
	public void teardown()
	{
		adminPermissionRepository.rollbackInsertedData();
	}
	
	private T insertEntity()
	{
		@SuppressWarnings("unchecked")
		T entity = (T) templateEntity.createClonedEntity();
		adminPermissionRepository.saveToDb(entity);
		return entity;
	}

	protected abstract BaseRepository<T> getAdminPermissionRepository();
	protected abstract T getTemplateEntity();
	protected abstract void modifyEntity(T entity);

}
