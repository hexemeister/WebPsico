package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.Anamnese;
import modelo.Contato;
import modelo.Endereco;
import modelo.Escolaridade;
import modelo.EstadoCivil;
import modelo.Evolucao;
import modelo.Indicacao;
import modelo.Paciente;
import modelo.Perfil;
import modelo.Sexo;
import modelo.Turno;
import modelo.Uf;
import modelo.Usuario;
import persistence.AnamneseDao;
import persistence.ContatoDao;
import persistence.EvolucaoDao;
import persistence.PacienteDao;
import persistence.PersistenceUtil;
import persistence.UsuarioDao;

public class Util {
	static EntityManager em = new PersistenceUtil().getEntityManager();
	static Paciente p1;
	static Contato c1, c2;
	static Usuario u1, u2, u3, u4;
	static Evolucao e1;
	static Anamnese a1;

	public static <T> Object getObjectSession(String attribute){        
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();    
	    HttpSession session = request.getSession(true);    
	    return session.getAttribute(attribute);               
	}
	public static UIComponent findComponent(String componente) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(componente);
	}
	
	public static void log(Object object) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("MyBean " + methodName + ": " + object);
    }
	
	/**
	 * popula Usuarios no banco
	 */
	public static void populaBancoComUsuarios() {
		u1 = new Usuario(null, "Renato", "123", "Renato Moraes",
				"hexemeister@gmail.com", Perfil.ADMINISTRADOR);
		u2 = new Usuario(null, "Viviane", "123",
				"Viviane Rose Val Porto", "rosevalporto@gmail.com",
				Perfil.PSICOLOGA);
		u3 = new Usuario(null, "Bruno", "123", "Bruno Fitzner",
				"brunofitzner1973@gmail.com", Perfil.ATENDENTE);
		u4 = new Usuario(null, "Pablo", "123", "Pablo Rangel",
				"pablo@gmail.com", Perfil.ATENDENTE, true);
		UsuarioDao udao = new UsuarioDao();
		List<Usuario> lista = new ArrayList<Usuario>();
		lista.add(u1);
		lista.add(u2);
		lista.add(u3);
		lista.add(u4);
		for (Usuario usuario : lista) {
			udao.create(usuario);
		}
		System.out.println("****************** FIM DE populaBancoComUsuarios ********************");
	}

	public static void populaBancoComContatos() {

		Contato c1 = new Contato.Builder().nome("Venina Val Porto")
											.email("venina@gmail.com")
											.sexo(Sexo.F)
											.dataNascimento(new Date(1945, 8, 21))
											.endereco(new Endereco(null,"Estrada Curipós", "JPA", "521","Jardim Clarice","Rio de Janeiro",Uf.RJ,"22000-000"))
											.cpf("12345678901")
											.telefoneFixo("2124477654")
											.telefoneCelular("21999998888")
											.naturalidade(Uf.RJ)
											.nacionalidade("Brasileiro")
											.estadoCivil(EstadoCivil.CASADO)
											.escolaridade(Escolaridade.MEDIO_COMPLETO)
											.profissao("Do lar")
											.religiao("Espírita")
											.parentesco("Avó")
											.obs("Ligar na parte da tarde.")
											.build();
		Contato c2 = new Contato.Builder().nome("Luiz Augusto Andrade de Moraes")
				.email("luiz@gmail.com")
				.sexo(Sexo.M)
				.dataNascimento(new Date(1952, 3, 16))
				.endereco(new Endereco.Builder()
								.logradouro("Rua Primeiros Sonhos")
								.numero("123")
								.bairro("Ilha do Governador")
								.complemento("Jardim Guanabara")
								.cidade("Rio de Janeiro")
								.cep("21920-321")
								.uf(Uf.RJ)
								.build()
							)
				.cpf("29633699720")
				.telefoneFixo("2133339999")
				.telefoneCelular("21888887777")
				.naturalidade(Uf.RJ)
				.nacionalidade("Brasileiro")
				.estadoCivil(EstadoCivil.VIUVO)
				.escolaridade(Escolaridade.MEDIO_COMPLETO)
				.profissao("Aposentado")
				.religiao("Católico")
				.parentesco("Avô")
				.build();
											
		ContatoDao cdao = new ContatoDao();

		List<Contato> lista = new ArrayList<Contato>();
		lista.add(c1);
		lista.add(c2);
		// lista.add(c3);
		// lista.add(c4);
		for (Contato contato : lista) {
			cdao.create(contato);
		}
		System.out.println("****************** FIM DE populaBancoComContatos ********************");
	}
	
	public static void populaBancoComPacientes() {

		p1 = new Paciente.Builder().id(null)
											.nome("Lucas Val Porto")
											.email("lucas@gmail.com")
											.sexo(Sexo.M)
											.dataNascimento(new Date(2005, 2, 11))
											.endereco(new Endereco(null,"Estrada do Dendê", "Ilha do Governador", "542","Tauá","Rio de Janeiro",Uf.RJ,"21920-000"))
											.cpf("12345678910")
											.telefoneFixo("2133932975")
											.telefoneCelular("21999998888")
											.naturalidade(Uf.RJ)
											.nacionalidade("Brasileiro")
											.estadoCivil(EstadoCivil.SOLTEIRO)
											.escolaridade(Escolaridade.FUNDAMENTAL_INCOMPLETO)
											.profissao("Estudante")
											.desativado(false)
											.indicacao(new Indicacao(null,null,"Regina Lucy","Médica"))
											.dataInicio(new Date(2008, 1, 1) )
											.dataUltimaSessao(new Date(2015, 3, 11,17,30))
											.preferenciaTurno(Turno.TARDE)
											.preco(320.)
											.build();
		Contato c1 = new ContatoDao().findById(1);
		Contato c2 = new ContatoDao().findById(2);
		List<Contato> listaContatos = new ArrayList<Contato>();
		listaContatos.add(c1);
		listaContatos.add(c2);
		p1.setContatos(listaContatos);
		PacienteDao pdao = new PacienteDao();

		List<Paciente> lista = new ArrayList<Paciente>();
		lista.add(p1);
		// lista.add(p2);
		// lista.add(p3);
		// lista.add(p4);
		for (Paciente paciente: lista) {
			pdao.update(paciente);
		}
		System.out.println("****************** FIM DE populaBancoComPacientes ********************");
	}

	public static void populaBancoComEvolucao() {
		e1 = new Evolucao(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
				+ " Suspendisse sagittis vehicula urna, vel pharetra augue. ",new PacienteDao().findById(1), u2, new Date(2015-1900, 3, 11,17,30));
		new EvolucaoDao().update(e1);
		System.out.println("****************** FIM DE populaBancoComEvolucao ********************");
	}
	
	public static void populaBancoComAnamnese() {
		a1 = new Anamnese(null,u2, new PacienteDao().findById(1),"resumo","queixa","sintoma","tratamento","medicamento",
				"cirurgia","acidente","vidaPessoal","familia","examePsico","atitude","humor",
				"expansao","retrato","negacao","linguagem","conciente","hipotese","escola",
				"irescola","gravidez","alergia","saude","alimentacao","sono","mania","obs");
		new AnamneseDao().update(a1);
		System.out.println("****************** FIM DE populaBancoComAnamnese ********************");
	}
}
