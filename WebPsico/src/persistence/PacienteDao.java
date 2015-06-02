package persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

	public Paciente findPacienteByNomeOfPsicologaLogada(String nome) {
		Paciente resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("SELECT p FROM Anamnese a JOIN a.paciente p JOIN Usuario u WHERE a.psicologa = u");
			query.setParameter("nome", nome);
			resp = (Paciente) query.getSingleResult();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}
	
	
	
}
