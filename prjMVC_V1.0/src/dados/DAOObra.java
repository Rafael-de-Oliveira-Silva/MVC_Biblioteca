package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOObra implements IDAO<Obra>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Obra> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Obra> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOObra() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Obra>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOObra.singleton
	 */
	public static IDAO<Obra> getSingleton() {
		if (DAOObra.singleton == null)
			DAOObra.singleton = new DAOObra();
		return DAOObra.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#salvar(dados.Obra)
	 */
	@Override
	public boolean salvar(Obra novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#remover(dados.Obra)
	 */
	@Override
	public boolean remover(Obra obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#atualizar(dados.Obra)
	 */
	@Override
	public boolean atualizar(Obra obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#recuperar(int)
	 */
	@Override
	public Obra recuperar(int pos) {
		int i = 0;
		for (Obra ob : this.listaObjs)
			if (i++ == pos)
				return ob;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPelaMatricula(java.lang.String)
	 */
	@Override
	public Obra recuperarPelaChave(Object isbn) {
		for (Obra ob : this.listaObjs)
			if (ob.getIsbn().equals(isbn))
				return ob;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOObra#getListaObjs()
	 */
	@Override
	public Obra[] getListaObjs() {
		return (Obra[]) this.listaObjs.toArray(new Obra[TAMANHO_MAXIMO]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.ISerializarDAO#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) throws IOException,	ClassNotFoundException {
		// Recupera o array de objetos
		this.listaObjs = (Set<Obra>) ois.readObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.ISerializarDAO#salvarObjetos(java.io.ObjectOutputStream)
	 */
	@Override
	public void salvarObjetos(ObjectOutputStream oos) throws IOException {
		// Salva o array de objetos
		oos.writeObject(this.listaObjs);
	}
}

