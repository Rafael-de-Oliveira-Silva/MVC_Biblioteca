package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.CtrlPrograma;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class JanelaPrincipal extends JFrame implements IViewerPrincipal{

	private JPanel contentPane;
	private CtrlPrograma ctrlPrg;

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma ctrl) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Desenvolvimento\\Software House_1.3.2020\\Desenvolvimento - Recursos\\Imagens de Apoio\\Icones\\Man-Grey-icon.png"));
		setBackground(SystemColor.textHighlight);
		this.ctrlPrg = ctrl;
		setTitle("Controle de Empr\u00E9stimos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 480);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setForeground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaption);
		menuBar.setBounds(0, 0, 699, 30);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastro");
		menuBar.add(mnNewMenu);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		
		JMenuItem mntmEditoras = new JMenuItem("Editoras...");
		mntmEditoras.setIcon(new ImageIcon("C:\\Desenvolvimento\\Software House_1.3.2020\\Desenvolvimento - Recursos\\Imagens de Apoio\\botoes\\48x24\\treenode_add24.bmp"));
		mntmEditoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlPrg.iniciarCasoDeUsoManterEditoras();
			}
		});
		mnNewMenu.add(mntmEditoras);
		
		JMenuItem mntmAssuntos = new JMenuItem("Assuntos...");
		mntmAssuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterAssuntos();
			}
		});
		mnNewMenu.add(mntmAssuntos);
		
		JMenuItem mntmAutores_1 = new JMenuItem("Autores...");
		mntmAutores_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterAutores();
			}
		});
		mnNewMenu.add(mntmAutores_1);
		
		JMenuItem mntmExemplares = new JMenuItem("Exemplares...");
		mntmExemplares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterExemplares();
			}
		});
		mnNewMenu.add(mntmExemplares);
		
		JMenuItem mntmObras = new JMenuItem("Obras...");
		mntmObras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterObras();
			}
		});
		mnNewMenu.add(mntmObras);
		
		JMenu mnOperao = new JMenu("Opera\u00E7\u00E3o");
		menuBar.add(mnOperao);
		
		JMenuItem mntmEmprstimo = new JMenuItem("Empr\u00E9stimo");
		mntmEmprstimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterEmprestimos();
			}
		});
		mnOperao.add(mntmEmprstimo);
		
		JMenu mnManuteno = new JMenu("Manuten\u00E7\u00E3o");
		menuBar.add(mnManuteno);
		
		JMenuItem mntmAlunos = new JMenuItem("Alunos...");
		mntmAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterAlunos();
			}
		});
		mnManuteno.add(mntmAlunos);
		
		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rios...");
		mntmFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterFuncionarios();
			}
		});
		mnManuteno.add(mntmFuncionrios);
		
		JMenu mnEncerrar = new JMenu("Logout");
		mnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(mnEncerrar);
		
		JMenuItem mntmEncerrar = new JMenuItem("Encerrar");
		mntmEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.terminar();
			}
		});
		mnEncerrar.add(mntmEncerrar);
		
		this.setVisible(true);
	}
}
