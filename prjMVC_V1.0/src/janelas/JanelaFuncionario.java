package janelas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterFuncionarios;
import dados.DadosException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaFuncionario extends JFrame implements IViewerFuncionario{

	//DECLARAÇÃO DOS ATRIBUTOS
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterFuncionarios ctrl;
		
	/**
	 * Indica se estou fazendo uma operação de inclusão ou alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfTelefone;
	private JTextField tfMatFunc;

	/**
	 * Create the frame.
	 */
	public JanelaFuncionario(ICtrlManterFuncionarios ctFunc) {
		setTitle("Funcion\u00E1rio");
		this.ctrl = ctFunc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 11, 173, 14);
		contentPane.add(lblCpf);
		
		tfCpf = new JTextField();
		tfCpf.setForeground(Color.BLUE);
		tfCpf.setColumns(10);
		tfCpf.setBounds(10, 24, 86, 20);
		contentPane.add(tfCpf);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 55, 118, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setForeground(Color.BLUE);
		tfNome.setColumns(10);
		tfNome.setBounds(10, 69, 303, 20);
		contentPane.add(tfNome);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 100, 173, 14);
		contentPane.add(lblEndereco);
		
		tfEndereco = new JTextField();
		tfEndereco.setForeground(Color.BLUE);
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(10, 114, 400, 20);
		contentPane.add(tfEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 145, 118, 14);
		contentPane.add(lblTelefone);
		
		tfTelefone = new JTextField();
		tfTelefone.setForeground(Color.BLUE);
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(10, 158, 86, 20);
		contentPane.add(tfTelefone);
		
		JLabel lblMatFunc = new JLabel("Matricula:");
		lblMatFunc.setBounds(10, 189, 173, 14);
		contentPane.add(lblMatFunc);
		
		tfMatFunc = new JTextField();
		tfMatFunc.setForeground(Color.BLUE);
		tfMatFunc.setColumns(10);
		tfMatFunc.setBounds(10, 208, 86, 20);
		contentPane.add(tfMatFunc);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(55, 236, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(238, 236, 154, 23);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}
	
	//MÉTODO PARA O BOTÃO OK
		public void executarOk() {
			// Recupero os valores digitados nos textfields
			String cpf = tfCpf.getText();
			String nome = tfNome.getText();
			String endereco = tfEndereco.getText();
			String telefone = tfTelefone.getText();
			String matFunc = tfMatFunc.getText();
			// Verifico qual é a operação que estou fazendo e notifico ao controlador
			try {
				if(!ehAlteração)
					ctrl.incluir(cpf, nome, endereco, telefone, matFunc);
				else
					ctrl.alterar(cpf, nome, endereco, telefone, matFunc);
			} catch(DadosException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}

		//MÉTODO PARA O BOTÃO CANCELAR
		public void executarCancelar() {
			if(!ehAlteração)
				ctrl.cancelarIncluir();
			else
				ctrl.cancelarAlterar();
		}
		
		/* (non-Javadoc)
		 * @see janelas.IViewerFuncionario#atualizarCampos(java.lang.String, java.lang.String)
		 */
		@Override
		public void atualizarCampos(String cpf, String nome, String endereco, String telefone, String matFunc) {
			this.tfCpf.setText(cpf);
			this.tfNome.setText(nome);
			this.tfEndereco.setText(endereco);
			this.tfTelefone.setText(telefone);
			this.tfMatFunc.setText(matFunc);
			this.ehAlteração = true;
		}

}
