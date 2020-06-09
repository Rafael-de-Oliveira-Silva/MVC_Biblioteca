package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterAutores;
import dados.DadosException;

public class JanelaExcluirAutor {
	
	private ICtrlManterAutores ctrl;
	
	private int opcao;
	

	public JanelaExcluirAutor(ICtrlManterAutores ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Autor " + selecionado);
		if(this.opcao == JOptionPane.YES_OPTION)
			try {
				this.ctrl.excluir();
			} catch (DadosException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		else
			this.ctrl.cancelarExcluir();
	}
	
	public int getOpcao(){
		return this.opcao;
	}
}