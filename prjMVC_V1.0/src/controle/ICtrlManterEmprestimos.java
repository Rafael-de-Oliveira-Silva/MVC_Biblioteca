package controle;

import dados.DadosException;

public interface ICtrlManterEmprestimos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Emprestimos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Emprestimos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Emprestimo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Emprestimo
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Emprestimo
	 */
	public abstract boolean incluir(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException;

	/**
	 * M�todo que inicia o processo de altera��o de um Emprestimo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Emprestimo
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Emprestimo
	 */
	public abstract boolean alterar(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException;

	/**
	 * M�todo que inicia o processo de exclus�o de um Emprestimo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Emprestimo
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Emprestimo
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
