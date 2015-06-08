package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	public final String PERSISTENCE_UNIT = "WebPsico";

	private final EntityManager entityManager;
	private final EntityManagerFactory factory;

	public PersistenceUtil() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		this.entityManager = factory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
