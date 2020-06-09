package controle;

import dados.Assunto;
import dados.DadosException;
import dados.Editora;

public interface ICtrlManterObras {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Obras.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Obras.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de uma Obra
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de uma Obra
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui uma Obra
	 */
	public abstract boolean incluir(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de uma Obra
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de uma Obra
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar uma Obra
	 */
	public abstract boolean alterar(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de uma Obra
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de uma Obra
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui uma Obra
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}