package controle;

import dados.Assunto;
import dados.DadosException;
import dados.Editora;

public interface ICtrlManterObras {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Obras.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Obras.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de uma Obra
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de uma Obra
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui uma Obra
	 */
	public abstract boolean incluir(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de uma Obra
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de uma Obra
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar uma Obra
	 */
	public abstract boolean alterar(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de uma Obra
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de uma Obra
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui uma Obra
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}