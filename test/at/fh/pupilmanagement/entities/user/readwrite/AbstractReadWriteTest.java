package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.BaseEntity;

public abstract class AbstractReadWriteTest<T extends BaseEntity>
{
	protected final static User lowerPermissionUser = new User("pupilmanagement_writer", "pupilmanagement_writer");
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
		T entity = (T) templateEntity.createClonedEntity();

		try
		{
			lowerPermissionRepository.saveToDb(entity);
		} catch (Exception e)
		{
			Assert.fail(e.getMessage());
		} finally
		{
			// Because it's not possible to rollback with just write
			// permissions!
			T insertedEntity = adminPermissionRepository.find(entity.getId());
			if (insertedEntity != null)
				adminPermissionRepository.deleteFromDb(insertedEntity);
		}
	}

	@Test
	public void testUpdateOperation()
	{
		T entity = insertEntity();
		T insertedEntity = lowerPermissionRepository.find(entity.getId());
		modifyEntity(insertedEntity);

		try
		{
			lowerPermissionRepository.commit();
		} catch (Exception e)
		{
			Assert.fail(e.getMessage());
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

	private T insertEntity()
	{
		@SuppressWarnings("unchecked")
		T entity = (T) templateEntity.createClonedEntity();
		adminPermissionRepository.saveToDb(entity);
		return entity;
	}

	protected abstract BaseRepository<T> getLowerPermissionRepository();

	protected abstract BaseRepository<T> getAdminPermissionRepository();

	protected abstract T getTemplateEntity();

	protected abstract void modifyEntity(T entity);
}
