package controle;

import dados.DadosException;

public interface ICtrlManterFuncionarios {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Funcionarios.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Funcionarios.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Funcionario
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Funcionario
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Funcionario
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Funcionario
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Funcionario
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Funcionario
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Funcionario
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Funcionario
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Funcionario
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}