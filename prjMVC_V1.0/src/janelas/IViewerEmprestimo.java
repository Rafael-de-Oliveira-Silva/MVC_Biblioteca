package janelas;

public interface IViewerEmprestimo {
	
	public abstract void atualizarCampos(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva);

	public void setVisible(boolean flag);

}