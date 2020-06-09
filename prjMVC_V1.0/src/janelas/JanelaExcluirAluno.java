package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterAlunos;
import dados.DadosException;

public class JanelaExcluirAluno {
	
	private ICtrlManterAlunos ctrl;
	
	private int opcao;
	

	public JanelaExcluirAluno(ICtrlManterAlunos ct, Object selecionado){
		this.ctrl = ct;
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Aluno " + selecionado);
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