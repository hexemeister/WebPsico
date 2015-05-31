package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;

import org.apache.naming.java.javaURLContextFactory;

import modelo.Paciente;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import persistence.PacienteDao;

@RequestScoped
@Named
public class RelatorioMB {

	private Connection con = null;

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

	public void gerarRelatorioUsuarios() {

		try {
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
			response.addHeader("Content-disposition", "filename=relatorio_contatos.pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(report);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
