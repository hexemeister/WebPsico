package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericDao<T extends EntidadeBase> implements
		IGenericDao<T>, Serializable {

	/**
	 * Classe abstrata genérica para dar poder de CRUD para quaisquer classes
	 * que herdarem dela
	 * 
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
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public T salvar(T entity) throws Exception {
		try {
			em.getTransaction().begin();
			if (entity.getId() == null) {
				em.persist(entity);
			} else {
				if (!em.contains(entity)) {
					if (em.find(classe, entity.getId()) == null) {
						throw new Exception("Erro ao atualizar dados de "
								+ classe);
					}
				}
				entity = em.merge(entity);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return entity;
	}

	public T createAndGetId(T entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return entity;
	}

	public T update(T entity) {
		em.getTransaction().begin();
		T entityBD = em.merge(entity);
		em.getTransaction().commit();
		return entityBD;
	}

	@Override
	public void update(T entity, Integer chave) {
		em.getTransaction().begin();
		T entityBD = em.find(classe, chave);
		em.detach(entityBD);
		entityBD = entity;
		em.merge(entityBD);
		em.getTransaction().commit();
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		T entityBD = em.merge(entity);
		em.remove(entityBD);
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
