package persistence;

import modelo.Contato;

public class ContatoDao extends GenericDao<Contato> {

	public ContatoDao() {
		super(Contato.class);
	}

	private static final long serialVersionUID = 1L;

}
