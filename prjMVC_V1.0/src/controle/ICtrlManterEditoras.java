package controle;

import dados.DadosException;

public interface ICtrlManterEditoras {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Editoras.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Editoras.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de uma Editora
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de uma Editora
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui uma Editora
	 */
	public abstract boolean incluir(String nome, String cidade) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de uma Editora
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de uma Editora
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar uma Editora
	 */
	public abstract boolean alterar(String nome, String cidade) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de uma Editora
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de uma Editora
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui uma Editora
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
