package controle;

import dados.DadosException;

public interface ICtrlManterExemplares {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Exemplares.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Exemplares.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Exemplar
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Exemplar
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Exemplar
	 */
	public abstract boolean incluir(String numero, Object obra, String situacao) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Exemplar
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Exemplar
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Exemplar
	 */
	public abstract boolean alterar(String numero, Object obra, String situacao) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Exemplar
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Exemplar
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Exemplar
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}