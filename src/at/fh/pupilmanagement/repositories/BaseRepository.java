package at.fh.pupilmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmangement.entities.BaseEntity;

public class BaseRepository<T extends BaseEntity>
{

	public static final String DB_UNIT_NAME = "PupilManagement";

	protected Class<T> entityClass = null;
	protected EntityManager entityManager;
	protected EntityManagerFactory emFactory;
	protected List<T> insertedEntities = new ArrayList<T>();

	public BaseRepository(Class<T> entityClass)
	{

		if (entityClass == null)
			throw new IllegalArgumentException(
					"ERROR: given entityClass is NULL!");

		this.entityClass = entityClass;

		emFactory = Persistence.createEntityManagerFactory(DB_UNIT_NAME);
		createEntityManager(emFactory);
	}

	public BaseRepository(Class<T> entityClass, User user)
	{

		if (entityClass == null)
			throw new IllegalArgumentException(
					"ERROR: given entityClass is NULL!");

		if (user == null)
			throw new IllegalArgumentException(
					"ERROR: given user is NULL!");

		this.entityClass = entityClass;

		emFactory = Persistence.createEntityManagerFactory(DB_UNIT_NAME,
				user.getUserProperties());
		createEntityManager(emFactory);
	}

	private void createEntityManager(EntityManagerFactory factory)
	{
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	public void saveToDb(T entity)
	{
		entityManager.persist(entity);
		commit();

		this.insertedEntities.add(entity);
	}

	public void commit()
	{
		try
		{
			entityManager.getTransaction().commit();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			entityManager.getTransaction().begin();
		}
	}

	public void deleteFromDb(T entity)
	{
		entity = getEntityManager().merge(entity);
		getEntityManager().remove(entity);
		commit();
	}

	public void closeConnetion()
	{
		if (entityManager != null)
			entityManager.close();
		if (emFactory != null)
			emFactory.close();
	}

	public T find(long id)
	{
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll()
	{
		TypedQuery<T> query = entityManager.createQuery(
				"SELECT entity FROM " + entityClass.getTypeName() + " entity"
						+ " ORDER BY entity.id", entityClass);

		return query.getResultList();
	}

	public void rollbackInsertedData()
	{
		for (T entity : this.insertedEntities)
		{
			deleteFromDb(entity);
		}
		this.insertedEntities.clear();
	}

	public void rollbackInsertedData(String sequenceName, long sequenceValue)
	{
		rollbackInsertedData();
		BaseRepository.setSequenceValue(sequenceName, sequenceValue);
	}

	public EntityManager getEntityManager()
	{
		return this.entityManager;
	}

	public Class<T> getEntityClass()
	{
		return this.entityClass;
	}

	// returns the last inserted sequence value
	public static long getLastTableId(String sequence)
	{
		return getNextSequenceValue(sequence) - 1;
	}

	public static long getNextSequenceValue(String sequence)
	{
		return (long) Persistence.createEntityManagerFactory(DB_UNIT_NAME)
				.createEntityManager()
				.createNativeQuery("SELECT nextval('" + sequence + "')")
				.getSingleResult();
	}

	public static void setSequenceValue(String sequence, long value)
	{
		Persistence
				.createEntityManagerFactory(DB_UNIT_NAME)
				.createEntityManager()
				.createNativeQuery(
						"SELECT setval('" + sequence + "', " + value + ")")
				.getSingleResult();
	}
}
