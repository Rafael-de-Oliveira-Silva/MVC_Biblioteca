package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOEditora implements IDAO<Editora>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Editora> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Editora> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOEditora() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Editora>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOEditora.singleton
	 */
	public static IDAO<Editora> getSingleton() {
		if (DAOEditora.singleton == null)
			DAOEditora.singleton = new DAOEditora();
		return DAOEditora.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#salvar(dados.Editora)
	 */
	@Override
	public boolean salvar(Editora novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#remover(dados.Editora)
	 */
	@Override
	public boolean remover(Editora obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#atualizar(dados.Editora)
	 */
	@Override
	public boolean atualizar(Editora obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#recuperar(int)
	 */
	@Override
	public Editora recuperar(int pos) {
		int i = 0;
		for (Editora ed : this.listaObjs)
			if (i++ == pos)
				return ed;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPeloNome(java.lang.String)
	 */
	@Override
	public Editora recuperarPelaChave(Object nome) {
		for (Editora ed : this.listaObjs)
			if (ed.getNome().equals(nome))
				return ed;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEditora#getListaObjs()
	 */
	@Override
	public Editora[] getListaObjs() {
		return (Editora[]) this.listaObjs.toArray(new Editora[TAMANHO_MAXIMO]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.ISerializarDAO#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		// Recupera o array de objetos
		this.listaObjs = (Set<Editora>) ois.readObject();
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

