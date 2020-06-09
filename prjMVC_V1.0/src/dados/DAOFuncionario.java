package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOFuncionario implements IDAO<Funcionario>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Funcionario> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Funcionario> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOFuncionario() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Funcionario>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOFuncionario.singleton
	 */
	public static IDAO<Funcionario> getSingleton() {
		if (DAOFuncionario.singleton == null)
			DAOFuncionario.singleton = new DAOFuncionario();
		return DAOFuncionario.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#salvar(dados.Funcionario)
	 */
	@Override
	public boolean salvar(Funcionario novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#remover(dados.Funcionario)
	 */
	@Override
	public boolean remover(Funcionario obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#atualizar(dados.Funcionario)
	 */
	@Override
	public boolean atualizar(Funcionario obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#recuperar(int)
	 */
	@Override
	public Funcionario recuperar(int pos) {
		int i = 0;
		for (Funcionario func : this.listaObjs)
			if (i++ == pos)
				return func;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPelaMatricula(java.lang.String)
	 */
	@Override
	public Funcionario recuperarPelaChave(Object matricula) {
		for (Funcionario func : this.listaObjs)
			if (func.getMatFunc().equals(matricula))
				return func;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOFuncionario#getListaObjs()
	 */
	@Override
	public Funcionario[] getListaObjs() {
		return (Funcionario[]) this.listaObjs.toArray(new Funcionario[TAMANHO_MAXIMO]);
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
		this.listaObjs = (Set<Funcionario>) ois.readObject();
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

