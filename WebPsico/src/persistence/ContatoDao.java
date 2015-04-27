package persistence;

import modelo.Contato;

public class ContatoDao extends GenericDao<Contato> {

	private static final long serialVersionUID = 1L;
	
	public ContatoDao() {
		super(Contato.class);
	}


}
