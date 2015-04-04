package teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import persistence.PersistenceUtil;
import persistence.UsuarioDao;
import modelo.Perfil;
import modelo.Usuario;

public class Util {

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
		UsuarioDao udao = new UsuarioDao();
		List<Usuario> lista = new ArrayList<Usuario>();
		lista.add(u1);
		lista.add(u2);
		lista.add(u3);
		for (Usuario usuario : lista) {
			udao.create(usuario);
		}
	}

}
