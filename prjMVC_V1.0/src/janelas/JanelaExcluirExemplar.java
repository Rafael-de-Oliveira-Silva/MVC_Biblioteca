package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterExemplares;
import dados.DadosException;

public class JanelaExcluirExemplar {
	
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterExemplares ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	
	
	/**
	 * Construtor que ir� colocar uma janela modal perguntando
	 * se o usu�rio deseja ou n�o excluir o empregado
	 * @param nome
	 */
	public JanelaExcluirExemplar(ICtrlManterExemplares ctExemplar, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ctExemplar;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Exemplar " + selecionado);
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
	
	/**
	 * Retorna a op��o indicada pelo usu�rio
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
	

}
