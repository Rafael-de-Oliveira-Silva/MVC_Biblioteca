package controle;

import dados.DadosException;

public interface ICtrlManterAutores {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Autores.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Autores.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Autor
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Autor
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Autor
	 */
	public abstract boolean incluir(String nome) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Autor
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Autor
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Autor
	 */
	public abstract boolean alterar(String nome) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Autor
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Autor
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Autor
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
