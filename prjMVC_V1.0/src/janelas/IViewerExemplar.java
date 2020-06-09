package janelas;

public interface IViewerExemplar {

	public abstract void atualizarCampos(String numero, Object obra, String situacao);
	
	public void setVisible(boolean flag);
}
