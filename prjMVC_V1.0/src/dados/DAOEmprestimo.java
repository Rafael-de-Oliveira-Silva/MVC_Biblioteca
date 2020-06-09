package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOEmprestimo implements IDAO<Emprestimo>, IDAOSerializavel {
	//
	// ATRIBUTOS
	//

	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAO<Emprestimo> singleton;

	/**
	 * Referência para o Set que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Set<Emprestimo> listaObjs;

	//
	// MÉTODOS
	//

	/**
	 * Construtor privado do DAO
	 */
	private DAOEmprestimo() {
		// Aloco memória para a coleção
		this.listaObjs = new TreeSet<Emprestimo>();
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * @return DAOEmprestimo.singleton
	 */
	public static IDAO<Emprestimo> getSingleton() {
		if (DAOEmprestimo.singleton == null)
			DAOEmprestimo.singleton = new DAOEmprestimo();
		return DAOEmprestimo.singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEmprestimo#salvar(dados.Emprestimo)
	 */
	@Override
	public boolean salvar(Emprestimo novo) {
		return this.listaObjs.add(novo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEmprestimo#remover(dados.Emprestimo)
	 */
	@Override
	public boolean remover(Emprestimo obj) {
		return this.listaObjs.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEmprestimo#atualizar(dados.Emprestimo)
	 */
	@Override
	public boolean atualizar(Emprestimo obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEmprestimo#recuperar(int)
	 */
	@Override
	public Emprestimo recuperar(int pos) {
		int i = 0;
		for (Emprestimo d : this.listaObjs)
			if (i++ == pos)
				return d;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAO#recuperarPelaData(java.lang.String)
	 */
	@Override
	public Emprestimo recuperarPelaChave(Object dataEmp) {
		for (Emprestimo a : this.listaObjs)
			if (a.getDataEmp().equals(dataEmp))
				return a;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dados.IDAOEmprestimo#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}


	public Emprestimo[] getListaObjs() {
		return (Emprestimo[]) this.listaObjs.toArray();
		
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
		this.listaObjs = (Set<Emprestimo>) ois.readObject();
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

