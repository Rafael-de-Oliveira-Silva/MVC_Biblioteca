package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOAluno implements IDAO<Aluno>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Refer�ncia para a �nica inst�ncia da classe que dever� existir
	 */
	private static IDAO<Aluno> singleton;

	/**
	 * Refer�ncia para o Set que apontar� para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Aluno> listaObjs;

	//
	// M�TODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOAluno() {
		// Aloco mem�ria para a cole��o
		this.listaObjs = new TreeSet<Aluno>();
	}

	/**
	 * M�todo para retornar a �nica inst�ncia existente do DAO
	 * @return DAOAluno.singleton
	 */
	public static IDAO<Aluno> getSingleton() {
		if (DAOAluno.singleton == null)
			DAOAluno.singleton = new DAOAluno();
		return DAOAluno.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#salvar(dados.Aluno)
	 */
	@Override
	public boolean salvar(Aluno novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#remover(dados.Aluno)
	 */
	@Override
	public boolean remover(Aluno obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#atualizar(dados.Aluno)
	 */
	@Override
	public boolean atualizar(Aluno obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#recuperar(int)
	 */
	@Override
	public Aluno recuperar(int pos) {
		int i = 0;
		for (Aluno a : this.listaObjs)
			if (i++ == pos)
				return a;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPelaMatricula(java.lang.String)
	 */
	@Override
	public Aluno recuperarPelaChave(Object matricula) {
		for (Aluno a : this.listaObjs)
			if (a.getMatricula().equals(matricula))
				return a;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOAluno#getListaObjs()
	 */
	@Override
	public Aluno[] getListaObjs() {
		return (Aluno[]) this.listaObjs.toArray(new Aluno[TAMANHO_MAXIMO]);
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
		this.listaObjs = (Set<Aluno>) ois.readObject();
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
