package controle;

import dados.DadosException;

public interface ICtrlManterFuncionarios {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Funcionarios.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Funcionarios.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Funcionario
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Funcionario
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Funcionario
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Funcionario
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Funcionario
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Funcionario
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Funcionario
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Funcionario
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Funcionario
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}