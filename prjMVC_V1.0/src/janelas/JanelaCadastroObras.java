package janelas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterObras;
import controle.ITabelavel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;

public class JanelaCadastroObras extends JFrame implements IViewerCadastroObras{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterObras ctrl;
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JanelaCadastroObras(ICtrlManterObras ctObra) {
		setResizable(false);
		setTitle("Obras");
		this.ctrl = ctObra;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 769, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 763, 231);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "T\u00CDTULO", "ANO", "EDITORA", "ASSUNTO", "AUTOR", "N\u00BA EXEMPLARES"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(2).setPreferredWidth(44);
		table.getColumnModel().getColumn(3).setPreferredWidth(118);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.getColumnModel().getColumn(5).setPreferredWidth(190);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Incluir");
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\floppy_drive_24_h.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarIncluir();
			}
		});
		button.setBounds(10, 244, 150, 40);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Excluir");
		button_1.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\recycle_bin_24.png"));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarExcluir();
			}
		});
		button_1.setBounds(203, 244, 150, 40);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Alterar");
		button_2.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\edit24_h.png"));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAlterar();
			}
		});
		button_2.setBounds(394, 244, 150, 40);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Sair");
		button_3.setIcon(new ImageIcon("C:\\Projetos Git\\MVC_Biblioteca\\prjMVC_V1.0\\src\\img\\exit24.png"));
		button_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTerminar();
			}
		});
		button_3.setBounds(577, 244, 150, 40);
		contentPane.add(button_3);
		
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarIncluir()
	 */
	@Override
	public void executarIncluir() {
		this.ctrl.iniciarIncluir();
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarExcluir()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarAlterar()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarTerminar()
	 */
	@Override
	public void executarTerminar() {
		ctrl.terminar();
	}
	
	/* (non-Javadoc)
	 * @see janelas.ICadastroEmpregados#limpar()
	 */
	@Override
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see janelas.ICadastroEmpregados#incluirLinha(controle.Tabelavel)
	 */
	@Override
	public void incluirLinha(ITabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}	
}
