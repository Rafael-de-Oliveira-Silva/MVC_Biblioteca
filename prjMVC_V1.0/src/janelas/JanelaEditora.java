package janelas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterEditoras;
import dados.DadosException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class JanelaEditora extends JFrame implements IViewerEditora{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEditoras ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCidade;

	/**
	 * Create the frame.
	 */
	public JanelaEditora(ICtrlManterEditoras ctEditora) {
		setResizable(false);
		setTitle("Editora");
		this.ctrl = ctEditora;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 11, 120, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setForeground(Color.BLUE);
		tfNome.setBounds(10, 29, 301, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCidade.setBounds(10, 60, 103, 14);
		contentPane.add(lblCidade);
		
		tfCidade = new JTextField();
		tfCidade.setForeground(Color.BLUE);
		tfCidade.setBounds(10, 75, 301, 20);
		contentPane.add(tfCidade);
		tfCidade.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\tick_24.png"));
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarOk();
			}
		});
		btnOk.setBounds(24, 123, 150, 40);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\stop24_h.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(197, 123, 150, 40);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}

	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String nome = tfNome.getText();
		String cidade = tfCidade.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(nome, cidade);
			else
				ctrl.alterar(nome, cidade);
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
	
	public void atualizarCampos(String nome, String cidade) {
		this.tfNome.setText(nome);
		this.tfCidade.setText(cidade);
		this.ehAlteração = true;
	}
	
}
