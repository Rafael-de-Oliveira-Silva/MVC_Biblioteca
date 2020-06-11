package janelas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controle.ICtrlManterAssuntos;
import controle.ITabelavel;
import java.awt.Font;

public class JanelaCadastroAssuntos extends JFrame implements IViewerCadastroAssuntos{
	
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterAssuntos ctrl;
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroAssuntos(ICtrlManterAssuntos ctAssunto) {
		setResizable(false);
		setTitle("Assuntos");
		this.ctrl = ctAssunto;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 665, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 659, 233);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DESCRI\u00C7\u00C3O", "N\u00BA OBRAS"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		scrollPane.setViewportView(table);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		btnIncluir.setBounds(10, 246, 150, 40);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		btnExcluir.setBounds(172, 246, 150, 40);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		btnAlterar.setBounds(334, 246, 150, 40);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		btnSair.setBounds(496, 246, 150, 40);
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
