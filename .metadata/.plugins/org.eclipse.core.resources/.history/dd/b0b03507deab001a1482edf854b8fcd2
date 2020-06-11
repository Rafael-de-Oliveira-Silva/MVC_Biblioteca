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

public class JanelaCadastroFuncionarios extends JFrame implements IViewerCadastroFuncionarios{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterFuncionarios ctrl;

	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroFuncionarios(ICtrlManterFuncionarios ctFuncionario) {
		setTitle("Funcion\u00E1rios");
		this.ctrl = ctFuncionario;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 494, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 478, 217);
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
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(50, 228, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(149, 228, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(248, 228, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		btnSair.setBounds(347, 228, 89, 23);
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
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclusão
		ctrl.iniciarExcluir(pos);	
	}
	
	public void executarAlterar() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de alteração
		ctrl.iniciarAlterar(pos);	
	}
	
	public void executarTerminar() {
		ctrl.terminar();
	}
	
}
