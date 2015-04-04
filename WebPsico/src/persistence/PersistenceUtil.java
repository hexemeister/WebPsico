package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	private final EntityManager entityManager;
	private final EntityManagerFactory factory;
	
	public PersistenceUtil() {
		factory = Persistence.createEntityManagerFactory("WebPsico");
		this.entityManager = factory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
}
