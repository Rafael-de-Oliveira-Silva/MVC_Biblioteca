package janelas;

import javax.swing.JOptionPane;

import controle.ICtrlManterExemplares;
import dados.DadosException;

public class JanelaExcluirExemplar {
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterExemplares ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o empregado
	 * @param nome
	 */
	public JanelaExcluirExemplar(ICtrlManterExemplares ctExemplar, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ctExemplar;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Exemplar " + selecionado);
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
	
	/**
	 * Retorna a opção indicada pelo usuário
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
	

}
