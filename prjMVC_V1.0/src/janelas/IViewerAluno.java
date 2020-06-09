package janelas;

public interface IViewerAluno {
	
	/**
	 * Determina os valores para os campos da janela
	 * @param cpf
	 * @param nome
	 */
	public abstract void atualizarCampos(String cpf, String nome, String endereco, String telefone, String matricula);

	public void setVisible(boolean flag);
}
