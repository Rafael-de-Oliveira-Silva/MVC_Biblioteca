package controle;

public interface ICtrlPrograma {

	/**
	 * Procedimentos executados no início do programa
	 */
	public abstract void iniciar();

	/**
	 * Procedimentos executados no final do programa
	 */
	public abstract void terminar();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Exemplares.
	 */
	public abstract boolean iniciarCasoDeUsoManterExemplares();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Exemplares.
	 */
	public abstract boolean terminarCasoDeUsoManterExemplares();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Editoras.
	 */
	public abstract boolean iniciarCasoDeUsoManterEditoras();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Editoras.
	 */
	public abstract boolean terminarCasoDeUsoManterEditoras();
	
	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Alunos.
	 */
	public abstract boolean iniciarCasoDeUsoManterAlunos();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Alunos.
	 */
	public abstract boolean terminarCasoDeUsoManterAlunos();

	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Autores.
	 */
	public abstract boolean iniciarCasoDeUsoManterAutores();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Autores.
	 */
	public abstract boolean terminarCasoDeUsoManterAutores();
	
	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Assuntos.
	 */
	public abstract boolean iniciarCasoDeUsoManterAssuntos();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Assuntos.
	 */
	public abstract boolean terminarCasoDeUsoManterAssuntos();
	
	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Emprestimos.
	 */
	public abstract boolean iniciarCasoDeUsoManterEmprestimos();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Emprestimos.
	 */
	public abstract boolean terminarCasoDeUsoManterEmprestimos();
	
	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Funcionarios.
	 */
	public abstract boolean iniciarCasoDeUsoManterFuncionarios();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Funcionarios.
	 */
	public abstract boolean terminarCasoDeUsoManterFuncionarios();
	
	/** Procedimentos executados pelo controlador do programa
	 * para iniciar o caso de uso Manter Obras.
	 */
	public abstract boolean iniciarCasoDeUsoManterObras();

	/**
	 * Procedimentos executados pelo controlador do programa
	 * para finalizar o caso de uso Manter Obras.
	 */
	public abstract boolean terminarCasoDeUsoManterObras();
	
}