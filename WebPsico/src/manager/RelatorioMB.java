package manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Paciente;
import modelo.Usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import persistence.PacienteDao;

@ViewScoped
@Named
public class RelatorioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con = null;

	private List<Paciente> listaPaciente;
	private Paciente pacienteSelecionado;

	@Inject
	private LoginMB loginMB;
	private Usuario logado;

	public RelatorioMB() {
	}

	@PostConstruct
	public void init() {
		logado = loginMB.getLogado();
	}

	public List<Paciente> getListaPaciente() {
		listaPaciente = new PacienteDao().findAll();
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public Usuario getLogado() {
		return logado = loginMB.getLogado();
	}

	public void gerarRelatorioUsuarios() {
		try {
			// EntityManagerFactory entityManagerFactory = Persistence
			// .createEntityManagerFactory(new
			// PersistenceUtil().PERSISTENCE_UNIT);
			// Map<String, Object> propertiesMap = entityManagerFactory
			// .getProperties();
			//
			// for (Map.Entry<String, Object> e : propertiesMap.entrySet()) {
			// System.out.println("key=" + e.getKey() + " value = "
			// + e.getValue().toString());
			// }
			// carregando o xml
			JasperDesign jd = JRXmlLoader.load(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/relatorios/usuarioRel.jrxml"));

			// gerando o arquivo jasper em tempo de execução
			JasperReport jasper = JasperCompileManager.compileReport(jd);

			// passando a conexão com o bd
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webpsicodb", "root",
					"admin123");

			Map parametros = new HashMap();
			parametros.put("REPORT_CONNECTION", con);

			// Preenchendo o relatorio
			JasperPrint jp = JasperFillManager.fillReport(jasper, parametros,
					con);

			// Gerando o pdf
			byte[] report = JasperExportManager.exportReportToPdf(jp);

			// Devolvendo pro navegador
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"filename=relatorio_usuarios.pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void gerarRelatorioPacientes() {

		try {
			// carregando o xml
			JasperDesign jd = JRXmlLoader.load(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/relatorios/pacienteRel.jrxml"));

			// gerando o arquivo jasper em tempo de execução
			JasperReport jasper = JasperCompileManager.compileReport(jd);

			// passando a conexão com o bd
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webpsicodb", "root",
					"admin123");
			Map parametros = new HashMap();
			parametros.put("REPORT_CONNECTION", con);

			// Preenchendo o relatorio
			JasperPrint jp = JasperFillManager.fillReport(jasper, parametros,
					con);

			// Gerando o pdf
			byte[] report = JasperExportManager.exportReportToPdf(jp);

			// Devolvendo pro navegador
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"filename=relatorio_pacientes.pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void gerarRelatorioContatos() {

		try {
			// carregando o xml
			JasperDesign jd = JRXmlLoader.load(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/relatorios/contatoRel.jrxml"));

			// gerando o arquivo jasper em tempo de execução
			JasperReport jasper = JasperCompileManager.compileReport(jd);

			// passando a conexão com o bd
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webpsicodb", "root",
					"admin123");
			Map parametros = new HashMap();
			parametros.put("REPORT_CONNECTION", con);

			// Preenchendo o relatorio
			JasperPrint jp = JasperFillManager.fillReport(jasper, parametros,
					con);

			// Gerando o pdf
			byte[] report = JasperExportManager.exportReportToPdf(jp);

			// Devolvendo pro navegador
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"filename=relatorio_contatos.pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void gerarRelatorioEvolucoes() {
		try {
			// carregando o xml
			JasperDesign jd = JRXmlLoader.load(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/relatorios/evolucaoRel.jrxml"));

			// gerando o arquivo jasper em tempo de execução
			JasperReport jasper = JasperCompileManager.compileReport(jd);

			// passando a conexão com o bd
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webpsicodb", "root",
					"admin123");
			Map parametros = new HashMap();
			parametros.put("REPORT_CONNECTION", con);
			parametros.put("ID_PACIENTE", pacienteSelecionado.getId());
			parametros.put("ID_PSICOLOGA", logado.getId());

			// Preenchendo o relatorio
			JasperPrint jp = JasperFillManager.fillReport(jasper, parametros,
					con);

			// Gerando o pdf
			byte[] report = JasperExportManager.exportReportToPdf(jp);

			// Devolvendo pro navegador
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"inline; filename=relatorio_evolucoes.pdf");
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void gerarRelatorioAnamnese() {
		try {
			// carregando o xml
			JasperDesign jd = JRXmlLoader.load(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/relatorios/anamneseRel.jrxml"));

			// gerando o arquivo jasper em tempo de execução
			JasperReport jasper = JasperCompileManager.compileReport(jd);

			// passando a conexão com o bd
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webpsicodb", "root",
					"admin123");
			Map parametros = new HashMap();
			parametros.put("REPORT_CONNECTION", con);
			parametros.put("ID_PACIENTE", pacienteSelecionado.getId());
			parametros.put("ID_PSICOLOGA", logado.getId());

			// Preenchendo o relatorio
			JasperPrint jp = JasperFillManager.fillReport(jasper, parametros,
					con);

			// Gerando o pdf
			byte[] report = JasperExportManager.exportReportToPdf(jp);

			// Devolvendo pro navegador
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"inline; filename=relatorio_anamnese.pdf");
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
