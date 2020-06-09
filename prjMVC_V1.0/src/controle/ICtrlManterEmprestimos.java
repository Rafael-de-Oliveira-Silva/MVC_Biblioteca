package controle;

import dados.DadosException;

public interface ICtrlManterEmprestimos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Emprestimos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Emprestimos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Emprestimo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Emprestimo
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Emprestimo
	 */
	public abstract boolean incluir(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException;

	/**
	 * Método que inicia o processo de alteração de um Emprestimo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Emprestimo
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Emprestimo
	 */
	public abstract boolean alterar(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException;

	/**
	 * Método que inicia o processo de exclusão de um Emprestimo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Emprestimo
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Emprestimo
	 */
	public abstract boolean excluir() throws DadosException;

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
