package ajudantes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import modelo.Contato;
import modelo.EstadoCivil;
import modelo.Perfil;
import modelo.Sexo;
import modelo.Usuario;
import persistence.ContatoDao;
import persistence.PersistenceUtil;
import persistence.UsuarioDao;

public class Util {
	static EntityManager em = new PersistenceUtil().getEntityManager();

	/**
	 * popula Usuarios no banco
	 */
	public static void populaBancoComUsuarios() {
		em.getTransaction().begin();
		em.createQuery("Delete from Usuario u").executeUpdate();
		em.getTransaction().commit();
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
	}

	public static void populaBancoComContatos() {
		em.getTransaction().begin();
		em.createQuery("Delete from Contato c").executeUpdate();
		em.getTransaction().commit();

		Contato c1 = new Contato(null, "Venina Val Porto", "venina@gmail.com",
				Sexo.F.name(), new GregorianCalendar(1945, 8, 21), "Estrada Curipós",
				"1111111", 65, "999996666", "Rio de Janeiro", "Brasileiro",
				EstadoCivil.CASADO, "Ensino Médio", "Do lar","Espírita", "Mãe");
		
		ContatoDao cdao = new ContatoDao();
		
		
		List<Contato> lista = new ArrayList<Contato>();
		lista.add(c1);
//		lista.add(c2);
//		lista.add(c3);
//		lista.add(c4);
		for (Contato contato : lista) {
			cdao.create(contato);
		}

	}
}
