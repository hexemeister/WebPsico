package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Paciente;
import modelo.Usuario;


public class PacienteDao extends GenericDao<Paciente> {

	private static final long serialVersionUID = 1L;

	EntityManager em = super.getEntityManager();
	Query query;
	
	Usuario logado;
	
	public PacienteDao() {
		super(Paciente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findAllPacientesAtivos() {
		List<Paciente> resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("SELECT DISTINCT p FROM Paciente p WHERE p.desativado = false");
			resp = query.getResultList();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findAllPacientesByNomeOfPsicologaLogada(Usuario user) {
		List<Paciente> resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("SELECT DISTINCT p FROM Anamnese a JOIN a.paciente p JOIN Usuario u WHERE a.psicologa = :user AND a.paciente.desativado = false");
			query.setParameter("user", user);
			resp = query.getResultList();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}
	
	
	
}
