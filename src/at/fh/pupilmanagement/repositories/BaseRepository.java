package at.fh.pupilmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import at.fh.pupilmangement.entities.BaseEntity;

public class BaseRepository<T extends BaseEntity>{

	public static final String DB_NAME = "PupilManagement";

	protected Class<T> entityClass = null;
	protected EntityManager entityManager;
	protected EntityManagerFactory emFactory;
	protected String sequenceName;
	protected long lastTableId;
	protected List<T> insertedEntities = new ArrayList<T>();

	public BaseRepository(Class<T> entityClass) {
		this.entityClass = entityClass;

		emFactory = Persistence.createEntityManagerFactory(DB_NAME);
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	public void saveToDb(T entity) {
		
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		
		this.insertedEntities.add(entity);
	}
	
	public boolean delete(T entity){
		try {
			
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
			getEntityManager().getTransaction().begin();
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean delete(long id){
		// TODO: implement if needed
		return false;
	}
	
	public void closeConnetion() {
		if (entityManager != null)
			entityManager.close();
		if (emFactory != null)
			emFactory.close();
	}

	public T find(long id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery(
				"SELECT entity FROM " + entityClass.getTypeName() + " entity"
						+ " ORDER BY entity.id", entityClass);

		return query.getResultList();
	}

	public void rollbackInsertedData(){
		for (T entity : this.insertedEntities) {
			delete(entity);
		}
	}
	
	public void rollbackInsertedData(String sequenceName, long sequenceValue){
		rollbackInsertedData();
		BaseRepository.setSequenceValue(sequenceName, sequenceValue);
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	public static long getNextSequenceValue(String sequence) {
		return (long) Persistence.createEntityManagerFactory(DB_NAME)
				.createEntityManager()
				.createNativeQuery("SELECT nextval('" + sequence + "')")
				.getSingleResult();
	}

	public static void setSequenceValue(String sequence, long value) {
		Persistence
				.createEntityManagerFactory(DB_NAME)
				.createEntityManager()
				.createNativeQuery(
						"SELECT setval('" + sequence + "', " + value + ")")
				.getSingleResult();
	}
}
