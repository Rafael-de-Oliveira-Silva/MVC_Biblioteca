package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterObras;
import dados.DadosException;

public class JanelaExcluirObra {
	
	private ICtrlManterObras ctrl;
	
	private int opcao;
	

	public JanelaExcluirObra(ICtrlManterObras ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover a Obra " + selecionado);
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
