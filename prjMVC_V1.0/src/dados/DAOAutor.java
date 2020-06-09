package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class DAOAutor implements IDAO<Autor>, IDAOSerializavel {
	 
	 ///////////////////////////////////////////////////////////////////////////////
	// ATRIBUTOS                                                                 //
   ///////////////////////////////////////////////////////////////////////////////

	//REFER�NCIA PARA A �NICA INST�NCIA DA CLASSE QUE DEVER� EXISTIR
	private static IDAO<Autor> 			singleton;

	//REFER�NCIA PARA UM 'SET' QUE APONTAR� PARA TODOS OS OBJETOS GUARDADOS PELO DAO
	private Set<Autor> 					listaObjs;

	 /////////////////////////////////////////////////////////////////////////////
	// M�TODOS                                                                 //
   /////////////////////////////////////////////////////////////////////////////

	/**
	 * M�TODO PRIVADO DO CONSTRUTOR DAOAUTOR  
	 */
	private DAOAutor() {
		// Aloco mem�ria para uma cole��o de Autores
		this.listaObjs = new TreeSet<Autor>();
	}

	/** 
	 * M�TODO QUE RETORNA OS AUTORES EXISTENTES NO DAO 
	 * @return DAOAutor.singleton
	 */
	public static IDAO<Autor> getSingleton() {
		if (DAOAutor.singleton == null)
			DAOAutor.singleton = new DAOAutor();
		return DAOAutor.singleton;
	}

	@Override
	public boolean salvar(Autor novo) {
		return this.listaObjs.add(novo);
	}

	@Override
	public boolean remover(Autor obj) {
		return this.listaObjs.remove(obj);
	}

	@Override
	public boolean atualizar(Autor obj) {
		return true;
	}

	@Override
	public Autor recuperar(int pos) {
		int i = 0;
		for (Autor d : this.listaObjs)
			if (i++ == pos)
				return d;
		return null;
	}

	@Override
	public Autor recuperarPelaChave(Object sigla) {
		for (Autor a : this.listaObjs)
			if (a.getNome().equals(sigla))
				return a;
		return null;
	}
	
	@Override
	public int getNumObjs() {
		return this.listaObjs.size();
	}

	@Override
	public Autor[] getListaObjs() {
		return (Autor[]) this.listaObjs.toArray(new Autor[TAMANHO_MAXIMO]);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void recuperarObjetos(ObjectInputStream ois) throws IOException,	ClassNotFoundException {
		// Recupera o array de objetos
		this.listaObjs =  (Set<Autor>) ois.readObject();
	}

	@Override
	public void salvarObjetos(ObjectOutputStream oos) throws IOException {
		// Salva o array de objetos
		oos.writeObject(this.listaObjs);
	}
}
