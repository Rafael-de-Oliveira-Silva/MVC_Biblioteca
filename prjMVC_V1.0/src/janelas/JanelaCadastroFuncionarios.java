package janelas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterFuncionarios;
import controle.ITabelavel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class JanelaCadastroFuncionarios extends JFrame implements IViewerCadastroFuncionarios{

	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterFuncionarios ctrl;

	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroFuncionarios(ICtrlManterFuncionarios ctFuncionario) {
		setResizable(false);
		setTitle("Funcion\u00E1rios");
		this.ctrl = ctFuncionario;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 730, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 724, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "NOME", "ENDERE\u00C7O", "TELEFONE", "MATRICULA"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(280);
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\floppy_drive_24_h.png"));
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(10, 234, 150, 40);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\recycle_bin_24.png"));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(192, 234, 150, 40);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\edit24_h.png"));
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(381, 234, 150, 40);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\exit24.png"));
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		btnSair.setBounds(562, 234, 150, 40);
		contentPane.add(btnSair);
		
		this.setVisible(true);
	}

	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	public void incluirLinha(ITabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}
	
	public void executarIncluir() {
		this.ctrl.iniciarIncluir();
	}
	
	public void executarExcluir() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclus�o
		ctrl.iniciarExcluir(pos);	
	}
	
	public void executarAlterar() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de altera��o
		ctrl.iniciarAlterar(pos);	
	}
	
	public void executarTerminar() {
		ctrl.terminar();
	}
	
}
