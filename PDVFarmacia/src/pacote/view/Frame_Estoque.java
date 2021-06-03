package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import pacote.dao.ProdutosDAO;
import pacote.dao.VendasDAO;
import pacote.model.Produtos;
import pacote.model.Vendas;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame_Estoque extends JFrame {

	private JPanel contentPane;
	public JTextField textDescricao;
	public JTextField textQtd;
	public JTextField textEstoqueAtual;
	private JTable table;
	int idproduto, qtd_nova;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Frame_Estoque.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Estoque frame = new Frame_Estoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo Listar na Tabela
	public void listar() {

		ProdutosDAO dao = new ProdutosDAO();
		List<Produtos> lista = dao.listarProdutos();
		DefaultTableModel dados = (DefaultTableModel) table.getModel();
		dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

		for (Produtos p : lista) {
			// pra cada linha vai adicionarum novo vetor de objetos
			dados.addRow(new Object[] { p.getId(), p.getDescricao(), p.getPreco(), p.getQtd_estoque(),
					p.getFornecedores().getNome() });

		}

	}

	/**
	 * Create the frame.
	 */
	public Frame_Estoque() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				listar();
			}
		});
		setBackground(Color.WHITE);
		setTitle("Detalhe da Venda");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 786, 478);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 780, 70);
		panel_top.setLayout(null);
		panel_top.setBackground(new Color(0, 128, 128));
		contentPane.add(panel_top);

		JLabel lblControleEstoque = new JLabel("Controle de Estoque de Produtos");
		lblControleEstoque.setForeground(Color.WHITE);
		lblControleEstoque.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblControleEstoque.setBounds(213, 23, 360, 29);
		panel_top.add(lblControleEstoque);

		JPanel painel_consulta_por_data = new JPanel();
		painel_consulta_por_data.setForeground(Color.WHITE);
		painel_consulta_por_data.setBounds(0, 73, 770, 118);
		painel_consulta_por_data.setBorder(
				new TitledBorder(null, "Consulta por Data", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		painel_consulta_por_data.setToolTipText("Dados da Venda");
		painel_consulta_por_data.setBackground(new Color(255, 255, 255));
		contentPane.add(painel_consulta_por_data);
		painel_consulta_por_data.setLayout(null);

		JLabel lblDescrição = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrição.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrição.setBounds(27, 32, 64, 26);
		painel_consulta_por_data.add(lblDescrição);

		// Define as máscaras
		MaskFormatter mascaradata = null;

		try {
			mascaradata = new MaskFormatter("##/##/####");

		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		JLabel lblEstoqueAtual = new JLabel("Estoque atual:");
		lblEstoqueAtual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoqueAtual.setBounds(27, 71, 88, 26);
		painel_consulta_por_data.add(lblEstoqueAtual);

		JLabel lblQtd = new JLabel("Qtd:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(262, 71, 40, 26);
		painel_consulta_por_data.add(lblQtd);

		textDescricao = new JTextField();
		textDescricao.setBounds(121, 34, 297, 26);
		painel_consulta_por_data.add(textDescricao);
		textDescricao.setColumns(10);

		textQtd = new JTextField();
		textQtd.setColumns(10);
		textQtd.setBounds(293, 73, 125, 26);
		painel_consulta_por_data.add(textQtd);

		textEstoqueAtual = new JTextField();
		textEstoqueAtual.setColumns(10);
		textEstoqueAtual.setBounds(121, 71, 131, 26);
		painel_consulta_por_data.add(textEstoqueAtual);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Botao pesquisar
						String nome = "%" + textDescricao.getText() + "%";

						ProdutosDAO dao = new ProdutosDAO();
						List<Produtos> lista = dao.buscarProdutosPorNome(nome);
						DefaultTableModel dados = (DefaultTableModel) table.getModel();
						dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

						for (Produtos p : lista) {
							// pra cada linha vai adicionarum novo vetor de objetos
							dados.addRow(new Object[] { p.getId(), p.getDescricao(), p.getPreco(), p.getQtd_estoque(),
									p.getFornecedores().getNome() });
						}

					}
				});
			}
		});
		btnPesquisar.setBounds(425, 34, 89, 27);
		painel_consulta_por_data.add(btnPesquisar);

		

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setToolTipText("Lista de Produtos:");
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 188, 770, 247);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 25, 750, 197);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Pega Dados
				idproduto = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				textDescricao.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				
				textEstoqueAtual.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Descri\u00E7\u00E3o", "Pre\u00E7o", "Qtd.Estoque", "Fornecedor" }));
		scrollPane.setViewportView(table);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int qtd_estoque, qtd;
					qtd_estoque = Integer.parseInt(textEstoqueAtual.getText());

					qtd = Integer.parseInt(textQtd.getText());

					qtd_nova = qtd_estoque + qtd;

					ProdutosDAO dao = new ProdutosDAO();

					dao.adicionarEstoque(idproduto, qtd_nova);

					JOptionPane.showMessageDialog(null, "Estoque do Produto Atualizado");

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Selecione o produto ou informe a nova quantidade." + erro);
				}
			}
		});
		btnAdicionar.setBounds(425, 73, 89, 26);
		painel_consulta_por_data.add(btnAdicionar);
	}
}
