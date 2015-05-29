package manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Paciente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import persistence.PacienteDao;
import persistence.PersistenceUtil;

@RequestScoped
@Named
public class RelatorioMB {

	private List<Paciente> listaPaciente;
	private Integer mbCodigoPaciente;

	private List<SelectItem> listaPacientesSI;

	// codigo e o nome do paciente (componente)

	public List<Paciente> getListaPaciente() {
		listaPaciente = new PacienteDao().findAll();
		return listaPaciente;
	}

	public List<SelectItem> getListaPacientesSI() {
		listaPacientesSI = new ArrayList<SelectItem>();
		for (Paciente p : new PacienteDao().findAll()) {
			listaPacientesSI.add(new SelectItem(p.getId(), p.getNome()));
		}
		return listaPacientesSI;
	}

	public void setListaPacientesSI(List<SelectItem> listaPacientesSI) {
		this.listaPacientesSI = listaPacientesSI;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public Integer getMbCodigoPaciente() {
		return mbCodigoPaciente;
	}

	public void setMbCodigoPaciente(Integer mbCodigoPaciente) {
		this.mbCodigoPaciente = mbCodigoPaciente;
	}

	public void gerarRelatorio() {
		try {
			InputStream arquivo = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/relpaciente.jasper");

			Map parameters = new HashMap();
			EntityManager em = new PersistenceUtil().getEntityManager();
			parameters.put(
					JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);

			// apontar para o template
			// HttpSession session = (HttpSession)
			// FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("IDPACIENTE", mbCodigoPaciente);
			byte report[] = JasperRunManager
					.runReportToPdf(arquivo, parameters);
			// JasperRunManager.runReportToHtmlFile("c:\rel.html",
			// parametros,
			// HibernateUtil.getSessionFactory().openSession().connection());
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pdf() throws JRException, IOException {
		EntityManager em = new PersistenceUtil().getEntityManager();
		Query query = em.createQuery("select s from ShoppingCart s");
		List listOfShoppingCart = (List) query.getResultList();
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				listOfShoppingCart);
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reports/report.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
				new HashMap(), beanCollectionDataSource);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
}
