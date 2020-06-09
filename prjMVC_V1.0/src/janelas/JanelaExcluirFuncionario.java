package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterFuncionarios;
import dados.DadosException;

public class JanelaExcluirFuncionario {
	
	private ICtrlManterFuncionarios ctrl;
	
	private int opcao;
	

	public JanelaExcluirFuncionario(ICtrlManterFuncionarios ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Funcionário " + selecionado);
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
