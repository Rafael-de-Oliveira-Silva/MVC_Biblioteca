package janelas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterExemplares;
import controle.ITabelavel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class JanelaCadastroExemplares extends JFrame implements IViewerCadastroExemplares{

	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterExemplares ctrl;
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroExemplares(ICtrlManterExemplares ctExemplar) {
		setResizable(false);
		setTitle("Exemplares");
		this.ctrl = ctExemplar;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 728, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 722, 225);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00DAMERO", "OBRA", "SITUA\u00C7\u00C3O"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(440);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(203);
		scrollPane.setViewportView(table);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\floppy_drive_24_h.png"));
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(26, 238, 150, 40);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\recycle_bin_24.png"));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(205, 238, 150, 40);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\edit24_h.png"));
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(380, 238, 150, 40);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\exit24.png"));
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		btnSair.setBounds(542, 238, 150, 40);
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
