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

import controle.ICtrlManterAutores;
import dados.DadosException;

public class JanelaAutor extends JFrame implements IViewerAutor{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAutores ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public JanelaAutor(ICtrlManterAutores ctAutor) {
		this.ctrl = ctAutor;
		setTitle("Autor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 155, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setForeground(Color.BLUE);
		tfNome.setBounds(10, 26, 331, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarOk();
			}
		});
		btnOk.setBounds(22, 68, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(205, 68, 154, 23);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}

	
	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String nome = tfNome.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(nome);
			else
				ctrl.alterar(nome);
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
	
	public void atualizarCampos(String nome) {
		this.tfNome.setText(nome);
		this.ehAlteração = true;
	}
}
