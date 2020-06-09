package controle;

import dados.DadosException;

public interface ICtrlManterAlunos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Alunos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Alunos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Aluno
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Aluno
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Aluno
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Aluno
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Aluno
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Aluno
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Aluno
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Aluno
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Aluno
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}