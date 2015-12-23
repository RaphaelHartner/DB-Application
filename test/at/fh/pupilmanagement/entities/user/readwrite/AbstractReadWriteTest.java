package at.fh.pupilmanagement.entities.user.readwrite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmanagement.repositories.BaseRepository;
import at.fh.pupilmangement.entities.BaseEntity;
import at.fh.pupilmangement.entities.Person;

public abstract class AbstractReadWriteTest<T extends BaseEntity>
{
	protected final static User writerUser = new User("pupilmanagement_writer", "pupilmanagement_writer");
	protected final static User adminUser = new User("pupilmanagement_admin", "harade14");
	protected BaseRepository<T> lowerPermissionRepository;
	protected BaseRepository<T> higherPermissionRepository;
	protected long lastTableId;
	protected T templateEntity;

	@Before
	public void setup()
	{
		lowerPermissionRepository = new BaseRepository<T>(getEntityClass(), writerUser);
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
		T entity = (T) templateEntity.createClonedEntity();
		
		try
		{
			lowerPermissionRepository.saveToDb(entity);
		} catch (Exception e)
		{
			Assert.fail();
		}
		finally{
			
			T insertedEntity = higherPermissionRepository.find(entity.getId());
			higherPermissionRepository.deleteFromDb(insertedEntity);
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
		} catch (Exception e)
		{
			Assert.fail();
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
		lowerPermissionRepository.closeConnetion();

		higherPermissionRepository.rollbackInsertedData();
		higherPermissionRepository.closeConnetion();
	}

	private T insertEntity()
	{
		@SuppressWarnings("unchecked")
		T entity = (T) templateEntity.createClonedEntity();
		higherPermissionRepository.saveToDb(entity);
		return entity;
	}

	protected abstract Class<T> getEntityClass();

	protected abstract T getTemplateEntity();

	protected abstract void modifyEntity(T entity);

	protected abstract String getSequenceName();

}
