package janelas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterEmprestimos;
import dados.Aluno;
import dados.DadosException;
import dados.Exemplar;
import dados.Funcionario;

public class JanelaEmprestimo extends JFrame implements IViewerEmprestimo{

	//DECLARAÇÃO DOS ATRIBUTOS
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEmprestimos ctrl;
		
	/**
	 * Indica se estou fazendo uma operação de inclusão ou alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfDataEmp;
	private JTextField tfHora;
	private JTextField tfDataDevPrev;
	private JTextField tfDataDevEfetiva;
	private JComboBox  cbFuncionarios;
	private JComboBox  cbAlunos;
	private JComboBox  cbExemplares;

	/**
	 * Create the frame.
	 */
	public JanelaEmprestimo(ICtrlManterEmprestimos ctEmp, Object[] funcionarios, Object[] alunos, Object[] exemplares) {
		setTitle("Empr\u00E9stimo");
		this.ctrl = ctEmp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataEmp = new JLabel("Data:");
		lblDataEmp.setBounds(9, 8, 169, 14);
		contentPane.add(lblDataEmp);
		
		tfDataEmp = new JTextField();
		tfDataEmp.setForeground(Color.BLUE);
		tfDataEmp.setBounds(9, 23, 86, 20);
		contentPane.add(tfDataEmp);
		tfDataEmp.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(9, 44, 86, 14);
		contentPane.add(lblHora);
		
		tfHora = new JTextField();
		tfHora.setForeground(Color.BLUE);
		tfHora.setColumns(10);
		tfHora.setBounds(9, 57, 86, 20);
		contentPane.add(tfHora);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(10, 80, 100, 14);
		contentPane.add(lblAluno);
		
		cbAlunos = new JComboBox();
		cbAlunos.setForeground(Color.BLUE);
		cbAlunos.setBounds(9, 94, 488, 20);
		contentPane.add(cbAlunos);
		//
		// Adicionando os alunos na combobox
		//
		for(Object o : alunos)
			if(o != null)
				cbAlunos.addItem(o);
		
		
		cbExemplares = new JComboBox();
		cbExemplares.setForeground(Color.BLUE);
		cbExemplares.setBounds(9, 131, 488, 20);
		contentPane.add(cbExemplares);
		//
		// Adicionando os exemplares na combobox
		//
		for(Object o : exemplares)
			if(o != null)
				cbExemplares.addItem(o);
		
		
		JLabel lblExemplar = new JLabel("Exemplar:");
		lblExemplar.setBounds(10, 117, 100, 14);
		contentPane.add(lblExemplar);
		
		JLabel lblDataDevPrevista = new JLabel("Data de Devolu\u00E7\u00E3o Prevista:");
		lblDataDevPrevista.setBounds(9, 156, 193, 14);
		contentPane.add(lblDataDevPrevista);
		
		tfDataDevPrev = new JTextField();
		tfDataDevPrev.setForeground(Color.BLUE);
		tfDataDevPrev.setColumns(10);
		tfDataDevPrev.setBounds(9, 169, 86, 20);
		contentPane.add(tfDataDevPrev);
		
		JLabel lblFuncionario = new JLabel("Funcion\u00E1rio:");
		lblFuncionario.setBounds(10, 191, 100, 14);
		contentPane.add(lblFuncionario);
		
		cbFuncionarios = new JComboBox();
		cbFuncionarios.setForeground(Color.BLUE);
		cbFuncionarios.setBounds(9, 205, 488, 20);
		contentPane.add(cbFuncionarios);
		//
		// Adicionando os exemplares na combobox
		//
		for(Object o : funcionarios)
			if(o != null)
				cbFuncionarios.addItem(o);
		
		
		JLabel lblDataDevEfetiva = new JLabel("Data de Devolu\u00E7\u00E3o Efetiva:");
		lblDataDevEfetiva.setBounds(9, 227, 169, 14);
		contentPane.add(lblDataDevEfetiva);
		
		tfDataDevEfetiva = new JTextField();
		tfDataDevEfetiva.setForeground(Color.BLUE);
		tfDataDevEfetiva.setColumns(10);
		tfDataDevEfetiva.setBounds(9, 242, 86, 20);
		contentPane.add(tfDataDevEfetiva);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarOk();
			}
		});
		btnOk.setBounds(94, 271, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(277, 271, 154, 23);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
	}
	
	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String dataEmp = tfDataEmp.getText();
		String horaEmp = tfHora.getText();
		Object aluno = cbAlunos.getSelectedItem();
		Object exemplar = cbExemplares.getSelectedItem();
		String dataDevPrev = tfDataDevPrev.getText();
		Object funcionario = cbFuncionarios.getSelectedItem();
		String dataDevEfetiva = tfDataDevEfetiva.getText();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(dataEmp, horaEmp, exemplar, aluno, dataDevPrev, funcionario, dataDevEfetiva);
			else
				ctrl.alterar(dataEmp, horaEmp, exemplar, aluno, dataDevPrev, funcionario, dataDevEfetiva);
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
	
	/* (non-Javadoc)
	 * @see janelas.IEmpregado#atualizarCampos(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void atualizarCampos(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) {
		this.tfDataEmp.setText(dataEmp);
		this.tfHora.setText(horaEmp);
		this.cbExemplares.setSelectedItem(exemplar);
		this.cbAlunos.setSelectedItem(aluno);
		this.tfDataDevPrev.setText(devPrevista);
		this.cbFuncionarios.setSelectedItem(funcionario);
		this.tfDataDevEfetiva.setText(devEfetiva);
		
		this.ehAlteração = true;
	}
}
