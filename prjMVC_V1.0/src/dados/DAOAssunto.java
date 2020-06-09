package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOAssunto implements IDAO<Assunto>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Assunto> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Assunto> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOAssunto() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Assunto>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOAssunto.singleton
	 */
	public static IDAO<Assunto> getSingleton() {
		if (DAOAssunto.singleton == null)
			DAOAssunto.singleton = new DAOAssunto();
		return DAOAssunto.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#salvar(dados.Assunto)
	 */
	@Override
	public boolean salvar(Assunto novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#remover(dados.Assunto)
	 */
	@Override
	public boolean remover(Assunto obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#atualizar(dados.Assunto)
	 */
	@Override
	public boolean atualizar(Assunto obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#recuperar(int)
	 */
	@Override
	public Assunto recuperar(int pos) {
		int i = 0;
		for (Assunto d : this.listaObjs)
			if (i++ == pos)
				return d;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPelaDescricao(java.lang.String)
	 */
	@Override
	public Assunto recuperarPelaChave(Object descricao) {
		for (Assunto a : this.listaObjs)
			if (a.getDescricao().equals(descricao))
				return a;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAssunto#getListaObjs()
	 */
	@Override
	public Assunto[] getListaObjs() {
		return (Assunto[]) this.listaObjs.toArray(new Assunto[TAMANHO_MAXIMO]);
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
		this.listaObjs = (Set<Assunto>) ois.readObject();
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

