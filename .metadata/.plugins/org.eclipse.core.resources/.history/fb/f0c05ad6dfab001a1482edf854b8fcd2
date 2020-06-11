package janelas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterEmprestimos;
import controle.ITabelavel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JanelaCadastroEmprestimos extends JFrame implements IViewerCadastroEmprestimos{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEmprestimos ctrl;
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroEmprestimos(ICtrlManterEmprestimos ctEmprestimo) {
		setTitle("Empr\u00E9stimos");
		this.ctrl = ctEmprestimo;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 562, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 546, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DATA EMP.", "HORA EMP.", "EXEMPLAR", "ALUNO", "DEV.PREVISTA", "FUNCIONARIO", "DEV.EFETIVA"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(95, 240, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(194, 240, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(293, 240, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		btnSair.setBounds(392, 240, 89, 23);
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
