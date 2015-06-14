package persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import modelo.Compromisso;

public class AgendaDao extends GenericDao<Compromisso> {
	private static final long serialVersionUID = 1L;

	public AgendaDao() {
		super(Compromisso.class);
	}

	public List<Compromisso> buscaPorData(Date inicio, Date fim) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Compromisso> cq = cb.createQuery(Compromisso.class);
		Root<Compromisso> r = cq.from(Compromisso.class);
		ParameterExpression<Date> dataInicio = cb.parameter(Date.class,
				"inicio");
		ParameterExpression<Date> dataFim = cb.parameter(Date.class, "fim");

		Expression<Date> data = r.get("dataMarcada");
		cq.where(cb.between(data, dataInicio, dataFim));

		TypedQuery<Compromisso> q = getEntityManager().createQuery(cq);
		q.setParameter("inicio", inicio);
		q.setParameter("fim", fim);
		return q.getResultList();

	}
}