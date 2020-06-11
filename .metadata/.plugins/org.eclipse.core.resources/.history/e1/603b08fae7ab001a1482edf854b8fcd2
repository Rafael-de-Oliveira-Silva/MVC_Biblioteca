package janelas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterAssuntos;
import dados.DadosException;

public class JanelaAssunto extends JFrame implements IViewerAssunto{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAssuntos ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfDescricao;

	/**
	 * Create the frame.
	 */
	public JanelaAssunto(ICtrlManterAssuntos ctAssunto) {
		setTitle("Assunto");
		this.ctrl = ctAssunto;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(10, 11, 162, 14);
		contentPane.add(lblDescricao);
		
		tfDescricao = new JTextField();
		tfDescricao.setForeground(Color.BLUE);
		tfDescricao.setBounds(10, 28, 341, 20);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(10, 68, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(193, 68, 154, 23);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}

	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String descricao = tfDescricao.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(descricao);
			else
				ctrl.alterar(descricao);
		} catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void executarCancelar() {
		if(!ehAlteração)
			ctrl.cancelarIncluir();
		else
			ctrl.cancelarAlterar();
	}
	
	public void atualizarCampos(String descricao) {
		this.tfDescricao.setText(descricao);
		this.ehAlteração = true;
	}
}
