package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {

	/**
	 * Classe concreta para persistir e criar pesquisas referente ao Usuario
	 * 
	 * @author Renato Moraes
	 */
	private static final long serialVersionUID = 1L;

	Query query;
	EntityManager em;

	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario findByLoginAndSenha(Usuario usuario) {
		Usuario resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("from Usuario u where u.login = :login and u.senha = :senha");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			resp = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByLoginOrNome(String string) {
		List<Usuario> resp = new ArrayList<Usuario>();
		try {
			em = super.getEntityManager();
			query = em.createQuery("from Usuario u where u.login LIKE '%"
					+ string + "%' OR u.nomeCompleto LIKE '%" + string + "%'");
			resp = query.getResultList();
		} catch (NoResultException e) {
			resp = new ArrayList<Usuario>();
		}
		return resp;
	}

	public Usuario findLogin(Usuario usuario) {
		Usuario resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("from Usuario u where u.login = :login");
			query.setParameter("login", usuario.getLogin());
			resp = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}
	
}
