package Persistence;

import java.util.List;

import javax.persistence.EntityManager;

public interface IGenericDao<T> {
	public void create (T entity);
	public void update (T entity);
	public void delete (T entity);
	public void deleteById (Integer id);
	public T FindById(Integer id);
	public List<T> findAll();
	public EntityManager getEntityManager();
}
