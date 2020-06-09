package controle;

import dados.DadosException;

public interface ICtrlManterAssuntos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Assuntos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Assuntos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Assunto
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Assunto
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Assunto
	 */
	public abstract boolean incluir(String descricao) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Assunto
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Assunto
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Assunto
	 */
	public abstract boolean alterar(String descricao) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Assunto
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Assunto
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Assunto
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
