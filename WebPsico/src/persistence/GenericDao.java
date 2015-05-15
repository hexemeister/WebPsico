package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericDao<T> implements IGenericDao<T>, Serializable {
	
	/**
	 * Classe abstrata genérica para dar poder de CRUD para quaisquer classes que herdarem dela 
	 * @author Renato Moraes
	 */
	private static final long serialVersionUID = 1L;

	private Class<T> classe;
	private EntityManager em;

	public GenericDao(Class<T> classe) {
		super();
		this.classe = classe;
		this.em = new PersistenceUtil().getEntityManager();
	}

	@Override
	public void create(T entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(T entity, Integer chave) {
		em.getTransaction().begin();
		entity = em.find(classe, chave);
		if (entity!=null) {
			em.merge(entity);
			em.getTransaction().commit();
			System.out.println("-------------- COMMIT!"+entity.toString());
		} else {
			System.out.println("+++++++++++++++ ROLL");
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();

	}

	@Override
	public void deleteById(Integer id) {
		T entity = findById(id);
		delete(entity);
	}

	@Override
	public T findById(Integer id) {
		em.getTransaction().begin();
		T entity = em.find(classe, id);
		em.getTransaction().commit();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getEntityManager().createQuery(
				"from " + classe.getSimpleName() + " u").getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
