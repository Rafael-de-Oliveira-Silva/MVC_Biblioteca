package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterObras;
import dados.Assunto;
import dados.DadosException;
import dados.Editora;
import dados.Exemplar;
import java.awt.Color;

public class JanelaObra extends JFrame implements IViewerObra{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterObras ctrl;
	
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfIsbn;
	private JTextField tfTitulo;
	private JTextField tfAno;
	private JComboBox  cbEditoras;
	private JComboBox  cbAssuntos;
	private JComboBox  cbAutores;
		
	/**
	 * Create the frame.
	 */
	public JanelaObra(ICtrlManterObras ctObra, Object[] editoras, Object[] assuntos, Object[] autores) {
		setTitle("Obra");
		this.ctrl = ctObra;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 11, 46, 14);
		contentPane.add(lblIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setForeground(Color.BLUE);
		tfIsbn.setBounds(10, 25, 86, 20);
		contentPane.add(tfIsbn);
		tfIsbn.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 50, 46, 14);
		contentPane.add(lblTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setForeground(Color.BLUE);
		tfTitulo.setBounds(10, 64, 325, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(10, 89, 46, 14);
		contentPane.add(lblAno);
		
		tfAno = new JTextField();
		tfAno.setForeground(Color.BLUE);
		tfAno.setBounds(10, 102, 46, 20);
		contentPane.add(tfAno);
		tfAno.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(10, 127, 168, 14);
		contentPane.add(lblEditora);
		
		cbEditoras = new JComboBox();
		cbEditoras.setForeground(Color.BLUE);
		cbEditoras.setBounds(10, 142, 238, 20);
		contentPane.add(cbEditoras);
		//
		// Adicionando as editoras na combobox
		//
		for(Object a : editoras)
			if(a != null)
				cbEditoras.addItem(a);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(10, 169, 168, 14);
		contentPane.add(lblAssunto);
		
		cbAssuntos = new JComboBox();
		cbAssuntos.setForeground(Color.BLUE);
		cbAssuntos.setBounds(10, 182, 238, 20);
		contentPane.add(cbAssuntos);
		//
		// Adicionando os assuntos na combobox
		//
		for(Object b : assuntos)
			if(b != null)
				cbAssuntos.addItem(b);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 213, 168, 14);
		contentPane.add(lblAutor);
				
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarOk();
			}
		});
		btnOk.setBounds(56, 257, 143, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCancelar();
			}
		});
		btnCancelar.setBounds(239, 257, 154, 23);
		contentPane.add(btnCancelar);
		
		cbAutores = new JComboBox();
		cbAutores.setForeground(Color.BLUE);
		cbAutores.setBounds(10, 231, 238, 20);
		contentPane.add(cbAutores);
		//
		// Adicionando os autores na combobox
		//
		for(Object c : autores)
			if(c != null)
				cbAutores.addItem(c);
		
		this.setVisible(true);
	}

	
	public void executarOk() {
		// Recupero os valores digitados nos textfields
		String isbn = tfIsbn.getText();
		String titulo = tfTitulo.getText();
		String ano = tfAno.getText();
		Editora editora = (Editora)cbEditoras.getSelectedItem();
		Assunto assunto = (Assunto)cbAssuntos.getSelectedItem();
		Object autor = cbAutores.getSelectedItem();
		// Verifico qual é a operação que estou fazendo
		// e notifico ao controlador
		try {
			if(!ehAlteração)
				ctrl.incluir(isbn, titulo, ano, editora, assunto, autor);
			else
				ctrl.alterar(isbn, titulo, ano, editora, assunto, autor);
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
	
	public void atualizarCampos(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) {
		this.tfIsbn.setText(isbn);
		this.tfTitulo.setText(titulo);
		this.tfAno.setText(ano);
		this.cbEditoras.setSelectedItem(editora);
		this.cbAssuntos.setSelectedItem(assunto);
		this.cbAutores.setSelectedItem(autor);
		this.ehAlteração = true;
	}	
}
