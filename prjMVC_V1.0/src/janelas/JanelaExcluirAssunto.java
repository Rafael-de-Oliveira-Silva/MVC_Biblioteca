package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterAssuntos;
import dados.DadosException;

public class JanelaExcluirAssunto {
	
	private ICtrlManterAssuntos ctrl;
	
	private int opcao;
	

	public JanelaExcluirAssunto(ICtrlManterAssuntos ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Assunto " + selecionado);
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
