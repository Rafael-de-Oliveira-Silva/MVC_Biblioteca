package controle;

import dados.DadosException;

public interface ICtrlManterEditoras {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Editoras.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Editoras.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de uma Editora
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de uma Editora
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui uma Editora
	 */
	public abstract boolean incluir(String nome, String cidade) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de uma Editora
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de uma Editora
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar uma Editora
	 */
	public abstract boolean alterar(String nome, String cidade) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de uma Editora
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de uma Editora
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui uma Editora
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
