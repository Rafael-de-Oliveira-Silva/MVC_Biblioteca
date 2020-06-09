package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterEmprestimos;
import dados.DadosException;

/**
 * Implementação da janela de confirmação de exclusão do emprestimo
 */
public class JanelaExcluirEmprestimo {
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEmprestimos ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	

	public JanelaExcluirEmprestimo(ICtrlManterEmprestimos ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Empréstimo " + selecionado);
		// Verifica o que o usuário indicou para ser feito
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