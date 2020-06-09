package controle;

import dados.DadosException;

public interface ICtrlManterExemplares {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Exemplares.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Exemplares.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Exemplar
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Exemplar
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Exemplar
	 */
	public abstract boolean incluir(String numero, Object obra, String situacao) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Exemplar
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Exemplar
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Exemplar
	 */
	public abstract boolean alterar(String numero, Object obra, String situacao) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Exemplar
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Exemplar
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Exemplar
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}