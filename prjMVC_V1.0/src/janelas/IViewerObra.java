package janelas;

import dados.Assunto;
import dados.Editora;

public interface IViewerObra {

	public abstract void atualizarCampos(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor);

	public void setVisible(boolean flag);
}
