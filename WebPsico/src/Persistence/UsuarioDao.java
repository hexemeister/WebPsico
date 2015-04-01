package Persistence;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import modelo.Usuario;

public class UsuarioDao extends GenericDao<Usuario>{

	Query query;
	EntityManager em;
	
	public UsuarioDao() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	}

	public Usuario findByLogin(Usuario usuario) {
		Usuario resp;
		try {
			em = super.getEntityManager();
			query = em.createQuery("from Usuario u where u.login = :login and u.senha = :senha");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			resp = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}

}
