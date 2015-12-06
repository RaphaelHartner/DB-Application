package at.fh.pupilmanagement.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Repository<T> {

	public static final String DB_NAME = "PupilManagement";

	protected Class<T> entityClass = null;
	protected EntityManager entityManager;
	protected EntityManagerFactory emFactory;
	protected String sequenceName;
	protected long lastIdInDatabase;

	public Repository(Class<T> entityClass) {
		this.entityClass = entityClass;
		
		emFactory = Persistence.createEntityManagerFactory(DB_NAME);
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	// TODO: implement constructor for testing purpose
	//use this constructor for testing purpose
	// call reset testing data when you are finish
//	public Repository(Class<T> entityClass, String sequenceName) {
//
//		this(entityClass);
//		this.sequenceName = sequenceName;
//		lastIdInDatabase = getNextSequenceValue(this.sequenceName) - 1;
//	}

	//TODO: implement automatically reset for test data
//	public void resetTestData() {
//		
//		entityManager.createQuery(
//				"DELETE FROM " + entityClass.getTypeName() + " entity WHERE entity.id > " + this.lastIdInDatabase, entityClass).executeUpdate();
//		setSequenceValue(this.sequenceName, lastIdInDatabase);
//	}

	public void closeConnetion() {
		if (entityManager != null)
			entityManager.close();
		if (emFactory != null)
			emFactory.close();
	}

	public T find(int id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery(
				"SELECT entity FROM " + entityClass.getTypeName() + " entity"
						+ " ORDER BY entity.id", entityClass);

		return query.getResultList();
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
