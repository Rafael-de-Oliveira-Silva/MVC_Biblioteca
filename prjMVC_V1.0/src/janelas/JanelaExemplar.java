package janelas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterExemplares;
import dados.DadosException;
import java.awt.Font;
import javax.swing.ImageIcon;

public class JanelaExemplar extends JFrame implements IViewerExemplar{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterExemplares ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfSituacao;
	private JComboBox  cbObras;
	
	/**
	 * Create the frame.
	 */
	public JanelaExemplar(ICtrlManterExemplares ctExemplar, Object[] obras) {
		setResizable(false);
		setTitle("Exemplar");
		this.ctrl = ctExemplar;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumero.setBounds(10, 11, 131, 14);
		contentPane.add(lblNumero);
		
		tfNumero = new JTextField();
		tfNumero.setForeground(new Color(0, 0, 255));
		tfNumero.setBounds(10, 25, 86, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);
		
		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSituacao.setBounds(10, 56, 120, 14);
		contentPane.add(lblSituacao);
		
		tfSituacao = new JTextField();
		tfSituacao.setForeground(Color.BLUE);
		tfSituacao.setBounds(10, 72, 131, 20);
		contentPane.add(tfSituacao);
		tfSituacao.setColumns(10);
		
		cbObras = new JComboBox();
		cbObras.setBounds(10, 117, 405, 20);
		contentPane.add(cbObras);
		//
		// Adicionando as obras na combobox
		//
		for(Object o : obras)
			if(o != null)
				cbObras.addItem(o);
		
		JLabel lblObra = new JLabel("Obra:");
		lblObra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblObra.setBounds(10, 103, 46, 14);
		contentPane.add(lblObra);
		
		JButton btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\tick_24.png"));
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarOk();
			}
		});
		btnOk.setBounds(47, 159, 143, 42);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\stop24_h.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(230, 159, 154, 42);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}

	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String numero = tfNumero.getText();
		Object obra = cbObras.getSelectedItem();
		String situacao = tfSituacao.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(numero, obra, situacao);
			else
				ctrl.alterar(numero, obra, situacao);
		} catch (DadosException e) {
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
	
	public void atualizarCampos(String numero, Object obra, String situacao) {
		this.tfNumero.setText(numero);
		this.cbObras.setSelectedItem(obra);
		this.tfSituacao.setText(situacao);
	
		this.ehAlteração = true;
	}
}
