package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOExemplar implements IDAO<Exemplar>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Exemplar> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Exemplar> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOExemplar() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Exemplar>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOExemplar.singleton
	 */
	public static IDAO<Exemplar> getSingleton() {
		if (DAOExemplar.singleton == null)
			DAOExemplar.singleton = new DAOExemplar();
		return DAOExemplar.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#salvar(dados.Exemplar)
	 */
	@Override
	public boolean salvar(Exemplar novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#remover(dados.Exemplar)
	 */
	@Override
	public boolean remover(Exemplar obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#atualizar(dados.Exemplar)
	 */
	@Override
	public boolean atualizar(Exemplar obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#recuperar(int)
	 */
	@Override
	public Exemplar recuperar(int pos) {
		int i = 0;
		for (Exemplar d : this.listaObjs)
			if (i++ == pos)
				return d;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPeloNumero(java.lang.String)
	 */
	@Override
	public Exemplar recuperarPelaChave(Object numero) {
		for (Exemplar ex : this.listaObjs)
			if (ex.getNumero().equals(numero))
				return ex;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOExemplar#getListaObjs()
	 */
	@Override
	public Exemplar[] getListaObjs() {
		return (Exemplar[]) this.listaObjs.toArray(new Exemplar[TAMANHO_MAXIMO]);
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
		this.listaObjs = (Set<Exemplar>) ois.readObject();
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

