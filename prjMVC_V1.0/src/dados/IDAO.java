package dados;


public interface IDAO<T> {

	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho máximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;

	/**
	 * Salva um objeto 
	 * @param novo
	 * @return
	 */
	public abstract boolean salvar(T novo);

	/**
	 * Remove um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean remover(T obj);

	/**
	 * Promove a atualização de um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean atualizar(T obj);

	/**
	 * Recupera um objeto pela posição
	 * @param posicao
	 * @return
	 */
	public abstract T recuperar(int posicao);

	/**
	 * Recupera um objeto pela chave
	 * @param sigla
	 * @return
	 */
	public abstract T recuperarPelaChave(Object chave);

	/**
	 * Retorna o número de objetos sendo gerenciados pelo DAO
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma cópia da lista de objetos
	 * @return
	 */
	public abstract T[] getListaObjs();
}