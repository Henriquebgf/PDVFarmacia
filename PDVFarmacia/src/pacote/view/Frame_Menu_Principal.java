package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import pacote.dao.FuncionariosDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame_Menu_Principal extends JFrame {
	public String usuariologado;
	
	public JMenuItem menu_item_posicaoDia_1;
	public JMenu menu_vendas;
	public JMenuItem menu_item_historicoVendas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Menu_Principal frame = new Frame_Menu_Principal();
					

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_Menu_Principal() {
		setResizable(false);
		setTitle("Tela de Menu");
		setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1201, 611);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setForeground(Color.BLACK);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 1185, 56);
		contentPane.add(menuBar);

		JMenu menu_clientes = new JMenu("Clientes");
		menu_clientes.setBackground(new Color(255, 255, 255));
		menu_clientes.setIcon(
				new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/iconfinder_38_Target_Audience_1688838.png")));
		menuBar.add(menu_clientes);

		JMenuItem menu_item_controleClientes = new JMenuItem("Controle de clientes");
		menu_item_controleClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Cliente tela = new Frame_Cliente();
				tela.setVisible(true);
			}
		});
		menu_clientes.add(menu_item_controleClientes);

		JMenu mnFuncionarios = new JMenu("Funcion\u00E1rios");
		mnFuncionarios.setIcon(new ImageIcon(
				Frame_Menu_Principal.class.getResource("/imagens/iconfinder_internt_web_technology-13_274900.png")));
		menuBar.add(mnFuncionarios);

		JMenuItem menu_item_controleFuncionarios = new JMenuItem("Controle de Funcion\u00E1rios");
		menu_item_controleFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Funcionarios tela = new Frame_Funcionarios();
				tela.setVisible(true);
			}
		});
		mnFuncionarios.add(menu_item_controleFuncionarios);

		JMenu menu_produtos = new JMenu("Produtos");
		menu_produtos.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/remedios.png")));
		menuBar.add(menu_produtos);

		JMenuItem menu_item_controleEstoque = new JMenuItem("Controle de Estoque");
		menu_item_controleEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Estoque tela = new Frame_Estoque();

				tela.setVisible(true);
			}
		});
		menu_produtos.add(menu_item_controleEstoque);

		JMenuItem menu_item_produtos = new JMenuItem("Consultar Produtos");
		menu_item_produtos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Menu consulta produto
				Frame_Produtos tela = new Frame_Produtos();

				tela.setVisible(true);
			}
		});
		menu_produtos.add(menu_item_produtos);

		menu_vendas = new JMenu("Vendas");
		menu_vendas.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/venda.png")));
		menuBar.add(menu_vendas);

		JMenuItem menu_item_vendas = new JMenuItem("Abrir Ponto de Vendas");
		menu_item_vendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Vendas tela = new Frame_Vendas();
				tela.setVisible(true);
			}
			
		});
		menu_vendas.add(menu_item_vendas);

		menu_item_posicaoDia_1 = new JMenuItem("Posi\u00E7\u00E3o do dia");
		menu_item_posicaoDia_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_TotalVendas tela = new Frame_TotalVendas();
				tela.setVisible(true);
			}
		});
		menu_item_posicaoDia_1.setBackground(Color.WHITE);
		menu_vendas.add(menu_item_posicaoDia_1);

		menu_item_historicoVendas = new JMenuItem("Hist\u00F3rico de Vendas");
		menu_item_historicoVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Historico tela = new Frame_Historico();
				tela.setVisible(true);
			}
		});
		menu_item_historicoVendas.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/calendario.png")));
		menu_vendas.add(menu_item_historicoVendas);

		JMenu menu_fornecedores = new JMenu("Fornecedores");
		menu_fornecedores.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/fornecedor.png")));
		menuBar.add(menu_fornecedores);

		JMenuItem menu_item_fornecedores = new JMenuItem("Controle de Fornecedores");
		menu_item_fornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Fornecedores tela = new Frame_Fornecedores();
				tela.setVisible(true);
			}
		});
		menu_fornecedores.add(menu_item_fornecedores);

		JMenu menu_configuraçoes = new JMenu("configurac\u00F5es");
		menu_configuraçoes.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/config.png")));
		menuBar.add(menu_configuraçoes);

		JMenuItem menu_item_trocarUsuario = new JMenuItem("Trocar de Usu\u00E1rio");
		menu_item_trocarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_Login telalogin = new Frame_Login();
				fechartela();
				telalogin.setVisible(true);
			}
		});
		menu_configuraçoes.add(menu_item_trocarUsuario);
		this.dispose();
		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int op;

				Object[] options = { "Confirmar", "Cancelar" };

				op = JOptionPane.showOptionDialog(null, "Você tem certeza que deseja sair?", "Selecionar uma opção",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (op == 0) {
					System.exit(0);
				}
			}
		});
		mnSair.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/sair.png")));
		menuBar.add(mnSair);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 53, 1185, 495);
		lblNewLabel.setIcon(new ImageIcon(Frame_Menu_Principal.class.getResource("/imagens/Telafundo2.png")));
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(0, 547, 1185, 25);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("usu\u00E1rio logado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 0, 106, 25);
		panel.add(lblNewLabel_1);

		JLabel label_usuario = new JLabel("");
		label_usuario.setForeground(UIManager.getColor("CheckBox.focus"));
		label_usuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_usuario.setBounds(105, 0, 213, 25);
		panel.add(label_usuario);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				label_usuario.setText(usuariologado);

			}
		});

	}

	public void fechartela() {
		this.dispose();
	};
}
