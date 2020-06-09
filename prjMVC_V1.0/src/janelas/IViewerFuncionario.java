package janelas;

public interface IViewerFuncionario {
    
	public abstract void atualizarCampos(String cpf, String nome, String endereco, String telefone, String matFunc);

	public void setVisible(boolean flag);
}
