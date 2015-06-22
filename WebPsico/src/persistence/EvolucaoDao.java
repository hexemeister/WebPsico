package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Evolucao;
import modelo.Paciente;
import modelo.Usuario;

public class EvolucaoDao extends GenericDao<Evolucao> {

	private static final long serialVersionUID = 1L;

	EntityManager em = super.getEntityManager();
	Query query;

	public EvolucaoDao() {
		super(Evolucao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Evolucao> findEvolucoesFromPacienteAndPsicologa(Paciente p,
			Usuario u) {
		List<Evolucao> resp;
		try {
			em = super.getEntityManager();
			query = em
					.createQuery("SELECT DISTINCT e FROM Evolucao e WHERE e.psicologa = :psicologa AND e.paciente = :paciente ORDER BY e.data ASC");
			query.setParameter("psicologa", u);
			query.setParameter("paciente", p);
			resp = query.getResultList();
		} catch (NoResultException e) {
			resp = null;
		}
		return resp;
	}
}
