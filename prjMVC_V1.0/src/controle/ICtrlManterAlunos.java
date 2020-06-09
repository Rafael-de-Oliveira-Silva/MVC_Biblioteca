package controle;

import dados.DadosException;

public interface ICtrlManterAlunos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Alunos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Alunos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Aluno
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Aluno
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Aluno
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Aluno
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Aluno
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Aluno
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Aluno
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Aluno
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Aluno
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}