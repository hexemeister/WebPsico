package persistence;

import modelo.Anamnese;

public class AnamneseDao extends GenericDao<Anamnese> {

	private static final long serialVersionUID = 1L;

	public AnamneseDao() {
		super(Anamnese.class);
	}

}
