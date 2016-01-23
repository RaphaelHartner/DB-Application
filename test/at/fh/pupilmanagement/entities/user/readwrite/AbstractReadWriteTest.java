package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.entities.BaseEntity;
import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;

public abstract class AbstractReadWriteTest<T extends BaseEntity>
{
	protected final static User writerPermissionUser = new User("pupilmanagement_writer", "pupilmanagement_writer");
	protected final static User adminPermissionUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> writerPermissionRepository;
	protected BaseRepository<T> adminPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;

	@Before
	public void setup()
	{
		writerPermissionRepository = getWriterPermissionRepository();
		adminPermissionRepository = getAdminPermissionRepository();
		templateEntity = getTemplateEntity();
	}

	@Test
	public void testFindOperation()
	{
		try
		{
			writerPermissionRepository.findAll();
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
			writerPermissionRepository.saveToDb(entity);
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
		T insertedEntity = writerPermissionRepository.find(entity.getId());
		modifyEntity(insertedEntity);

		try
		{
			writerPermissionRepository.commit();
		} catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteOperation()
	{
		T insertedEntity = writerPermissionRepository.find(insertEntity().getId());

		try
		{
			writerPermissionRepository.deleteFromDb(insertedEntity);
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

	protected abstract BaseRepository<T> getWriterPermissionRepository();
	protected abstract BaseRepository<T> getAdminPermissionRepository();
	protected abstract T getTemplateEntity();
	protected abstract void modifyEntity(T entity);
}
