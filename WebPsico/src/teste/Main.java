package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import modelo.Usuario;
import Persistence.PersistenceUtil;
import Persistence.UsuarioDao;

public class Main {

	public static void main(String[] args) {
		EntityManager em = new PersistenceUtil().getEntityManager();
		Usuario u = new Usuario(null,"Renato","123");
		
		UsuarioDao udao = new UsuarioDao();
		udao.create(u);
		System.out.println(udao.findByLogin(u).getLogin());
	}
	
}
