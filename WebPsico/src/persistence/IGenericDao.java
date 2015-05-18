package persistence;

import java.util.List;

import javax.persistence.EntityManager;

public interface IGenericDao<T> {
	public void create (T entity);
	public T createAndGetId (T entity);
	public void update (T entity, Integer chave);
	public void delete (T entity);
	public void deleteById (Integer id);
	public T findById(Integer id);
	public List<T> findAll();
	public EntityManager getEntityManager();
	public T update(T entity);
}
