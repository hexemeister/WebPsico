package Persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericDao<T> implements IGenericDao<T>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Class<T> classe;
	private EntityManager em;

	public GenericDao(Class<T> classe){
		super();
		this.classe = classe;
		this.em = new PersistenceUtil().getEntityManager();
	}

	@Override
	public void create(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
		
	}

	@Override
	public void deleteById(Integer id) {
		T entity = FindById(id);
		delete(entity);
	}

	@Override
	public T FindById(Integer id) {
		em.getTransaction().begin();
		T entity = em.find(classe, id);
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("from" + classe.getName()).getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	
	
}
