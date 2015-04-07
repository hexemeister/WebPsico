package ajudantes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import modelo.Perfil;
import modelo.Usuario;
import persistence.PersistenceUtil;
import persistence.UsuarioDao;

public class Util {

	/**
	 * popula Usuarios no banco
	 */
	public static void populaBancoComUsuarios() {
		EntityManager em = new PersistenceUtil().getEntityManager();
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

}
