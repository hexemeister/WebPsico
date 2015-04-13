package ajudantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import modelo.Contato;
import modelo.Endereco;
import modelo.Escolaridade;
import modelo.EstadoCivil;
import modelo.Indicacao;
import modelo.Paciente;
import modelo.Perfil;
import modelo.Sexo;
import modelo.Telefone;
import modelo.TipoTelefone;
import modelo.Turno;
import modelo.Uf;
import modelo.Usuario;
import persistence.ContatoDao;
import persistence.PacienteDao;
import persistence.PersistenceUtil;
import persistence.UsuarioDao;

public class Util {
	static EntityManager em = new PersistenceUtil().getEntityManager();

	/**
	 * popula Usuarios no banco
	 */
	public static void populaBancoComUsuarios() {
		Usuario u1 = new Usuario(null, "Renato", "123", "Renato Moraes",
				"hexemeister@gmail.com", Perfil.ADMINISTRADOR);
		Usuario u2 = new Usuario(null, "Viviane", "123",
				"Viviane Rose Val Porto", "rosevalporto@gmail.com",
				Perfil.PSICOLOGA);
		Usuario u3 = new Usuario(null, "Bruno", "123", "Bruno Fitzner",
				"brunofitzner1973@gmail.com", Perfil.ATENDENTE);
		Usuario u4 = new Usuario(null, "Pablo", "123", "Pablo Rangel",
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
											.sexo(Sexo.FEMININO)
											.dataNascimento(new GregorianCalendar(1945, 8, 21))
											.endereco(new Endereco(null,"Estrada Curipós", "JPA", "521","Jardim Clarice","Rio de Janeiro",Uf.RJ,"22000-000"))
											.cpf("11111111")
											.telefones(new ArrayList<Telefone>(Arrays.asList(new Telefone(null,"21","24477654",TipoTelefone.RESIDENCIAL))))
											.naturalidade(Uf.RJ)
											.nacionalidade("Brasileiro")
											.estadoCivil(EstadoCivil.CASADO)
											.escolaridade(Escolaridade.MEDIO_COMPLETO)
											.profissao("Do lar")
											.religiao("Espírita")
											.parentesco("Mãe")
											.desativado(false)
											.build();
											
		ContatoDao cdao = new ContatoDao();

		List<Contato> lista = new ArrayList<Contato>();
		lista.add(c1);
		// lista.add(c2);
		// lista.add(c3);
		// lista.add(c4);
		for (Contato contato : lista) {
			cdao.create(contato);
		}
		System.out.println("****************** FIM DE populaBancoComContatos ********************");
	}
	
	public static void populaBancoComPacientes() {

		Paciente p1 = new Paciente.Builder().id(null)
											.nome("Lucas Val Porto")
											.email("lucas@gmail.com")
											.sexo(Sexo.MASCULINO)
											.dataNascimento(new GregorianCalendar(2005, 5, 11))
											.endereco(new Endereco(null,"Estrada do Dendê", "Ilha do Governador", "542","Tauá","Rio de Janeiro",Uf.RJ,"21920-000"))
											.cpf("123456789")
											.telefones(new ArrayList<Telefone>(Arrays.asList(new Telefone(null,"21","33932975",TipoTelefone.RESIDENCIAL)) ))
											.naturalidade(Uf.RJ)
											.nacionalidade("Brasileiro")
											.estadoCivil(EstadoCivil.SOLTEIRO)
											.escolaridade(Escolaridade.FUNDAMENTAL_INCOMPLETO)
											.profissao("Estudante")
											.desativado(false)
											.indicacao(new Indicacao(null,null,"Regina Lucy","Médica"))
											.dataInicio(new GregorianCalendar(2008, 1, 1) )
											.dataUtimaSessao(new GregorianCalendar(2015, 5, 11))
											.frequencia("15 em 15 dias")
											.preferenciaTurno(Turno.TARDE)
											.preco(320.)
											.build();
		
		PacienteDao pdao = new PacienteDao();

		List<Paciente> lista = new ArrayList<Paciente>();
		lista.add(p1);
		// lista.add(c2);
		// lista.add(c3);
		// lista.add(c4);
		for (Paciente paciente: lista) {
			pdao.create(paciente);
		}
		System.out.println("****************** FIM DE populaBancoComPacientes ********************");
	}
}