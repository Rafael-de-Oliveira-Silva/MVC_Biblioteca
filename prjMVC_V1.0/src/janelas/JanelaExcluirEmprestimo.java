package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterEmprestimos;
import dados.DadosException;

/**
 * Implementa��o da janela de confirma��o de exclus�o do emprestimo
 */
public class JanelaExcluirEmprestimo {
	
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterEmprestimos ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	

	public JanelaExcluirEmprestimo(ICtrlManterEmprestimos ct, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Empr�stimo " + selecionado);
		// Verifica o que o usu�rio indicou para ser feito
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