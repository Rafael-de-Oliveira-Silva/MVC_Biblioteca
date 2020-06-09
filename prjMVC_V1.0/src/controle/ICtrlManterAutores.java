package controle;

import dados.DadosException;

public interface ICtrlManterAutores {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Autores.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Autores.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Autor
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Autor
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Autor
	 */
	public abstract boolean incluir(String nome) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Autor
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Autor
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Autor
	 */
	public abstract boolean alterar(String nome) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Autor
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Autor
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Autor
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
