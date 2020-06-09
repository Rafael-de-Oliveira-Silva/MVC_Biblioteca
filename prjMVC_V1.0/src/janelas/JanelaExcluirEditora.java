package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterEditoras;
import dados.DadosException;

public class JanelaExcluirEditora {
	
	private ICtrlManterEditoras ctrl;
	
	private int opcao;
	

	public JanelaExcluirEditora(ICtrlManterEditoras ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover a Editora " + selecionado);
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