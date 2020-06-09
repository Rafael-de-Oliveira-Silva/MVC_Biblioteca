package controle;

import dados.DadosException;

public interface ICtrlManterAssuntos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Assuntos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Assuntos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Assunto
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Assunto
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Assunto
	 */
	public abstract boolean incluir(String descricao) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Assunto
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Assunto
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Assunto
	 */
	public abstract boolean alterar(String descricao) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Assunto
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Assunto
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Assunto
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
