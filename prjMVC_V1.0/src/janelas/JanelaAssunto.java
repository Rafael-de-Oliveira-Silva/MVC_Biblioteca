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
import javax.swing.ImageIcon;
import java.awt.Font;

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
		setResizable(false);
		setTitle("Assunto");
		this.ctrl = ctAssunto;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescricao.setBounds(10, 11, 162, 14);
		contentPane.add(lblDescricao);
		
		tfDescricao = new JTextField();
		tfDescricao.setForeground(Color.BLUE);
		tfDescricao.setBounds(10, 28, 341, 20);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnOk.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\tick_24.png"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(10, 68, 150, 40);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\stop24_h.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(193, 68, 150, 40);
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
