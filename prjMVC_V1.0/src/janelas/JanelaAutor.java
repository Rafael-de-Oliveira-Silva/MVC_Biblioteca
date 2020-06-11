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
import javax.swing.ImageIcon;
import java.awt.Font;

public class JanelaAutor extends JFrame implements IViewerAutor{

	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterAutores ctrl;
	
	/**
	 * Indica se estou fazendo uma opera��o de inclus�o ou 
	 * altera��o
	 */
	private boolean ehAltera��o;
	
	private JPanel contentPane;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public JanelaAutor(ICtrlManterAutores ctAutor) {
		setResizable(false);
		this.ctrl = ctAutor;
		setTitle("Autor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 11, 155, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setForeground(Color.BLUE);
		tfNome.setBounds(10, 26, 331, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnOk.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\tick_24.png"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarOk();
			}
		});
		btnOk.setBounds(66, 85, 150, 40);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\stop24_h.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(279, 85, 150, 40);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}

	
	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String nome = tfNome.getText();
		// Verifico qual � a opera��o que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAltera��o)
				ctrl.incluir(nome);
			else
				ctrl.alterar(nome);
		} catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void executarCancelar() {
		if(!ehAltera��o)
			ctrl.cancelarIncluir();
		else
			ctrl.cancelarAlterar();
	}
	
	public void atualizarCampos(String nome) {
		this.tfNome.setText(nome);
		this.ehAltera��o = true;
	}
}
