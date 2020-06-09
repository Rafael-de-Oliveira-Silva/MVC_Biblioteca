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

import controle.ICtrlManterAlunos;
import dados.DadosException;

public class JanelaAluno extends JFrame implements IViewerAluno{

	//DECLARAÇÃO DOS ATRIBUTOS
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAlunos ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfTelefone;
	private JTextField tfMatricula;

	/**
	 * Create the frame.
	 */
	public JanelaAluno(ICtrlManterAlunos ctAluno) {
		setTitle("Aluno");
		this.ctrl = ctAluno;
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
		tfCpf.setBounds(10, 24, 86, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 55, 118, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setForeground(Color.BLUE);
		tfNome.setBounds(10, 69, 303, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 100, 173, 14);
		contentPane.add(lblEndereco);
		
		tfEndereco = new JTextField();
		tfEndereco.setForeground(Color.BLUE);
		tfEndereco.setBounds(10, 114, 400, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 145, 118, 14);
		contentPane.add(lblTelefone);
		
		tfTelefone = new JTextField();
		tfTelefone.setForeground(Color.BLUE);
		tfTelefone.setBounds(10, 158, 86, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 189, 173, 14);
		contentPane.add(lblMatricula);
		
		tfMatricula = new JTextField();
		tfMatricula.setForeground(Color.BLUE);
		tfMatricula.setBounds(10, 208, 86, 20);
		contentPane.add(tfMatricula);
		tfMatricula.setColumns(10);
		
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
		String matricula = tfMatricula.getText();
		// Verifico qual é a operação que estou fazendo e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(cpf, nome, endereco, telefone, matricula);
			else
				ctrl.alterar(cpf, nome, endereco, telefone, matricula);
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
	 * @see janelas.IViewerAluno#atualizarCampos(java.lang.String, java.lang.String)
	 */
	@Override
	public void atualizarCampos(String cpf, String nome, String endereco, String telefone, String matricula) {
		this.tfCpf.setText(cpf);
		this.tfNome.setText(nome);
		this.tfEndereco.setText(endereco);
		this.tfTelefone.setText(telefone);
		this.tfMatricula.setText(matricula);
		this.ehAlteração = true;
	}
}
