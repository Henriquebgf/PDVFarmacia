package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import pacote.dao.ClientesDAO;
import pacote.dao.FornecedoresDAO;
import pacote.dao.ProdutosDAO;
import pacote.model.Clientes;
import pacote.model.Fornecedores;
import pacote.model.Produtos;
import pacote.model.Utilitarios;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Window.Type;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.DropMode;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Frame_Vendas extends JFrame {


    Clientes obj = new Clientes();
    double total, preco,subtotal;
    int quantidade;
    
    DefaultTableModel carrinho;
    
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textData;
	private JTextField textProduto;
	private JTextField textQuantidade;
	private JTextField textCodigo;
	private JTextField textPreco;
	private JTextField textTotal;
	private JTable tabelaItens;
	private JTable table_1;

	

	public static void main(String[] args) {
		/*
		 * try { for (javax.swing.UIManager.LookAndFeelInfo info :
		 * javax.swing.UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } } } catch
		 * (ClassNotFoundException | InstantiationException | IllegalAccessException |
		 * javax.swing.UnsupportedLookAndFeelException ex) {
		 * java.util.logging.Logger.getLogger(Frame_Vendas.class.getName()).log(java.
		 * util.logging.Level.SEVERE, null, ex); }
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Frame_Vendas frame = new Frame_Vendas();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void escontertela() {
		this.dispose();
	}

	/**
	 * Criação do frame.
	 */
	public Frame_Vendas() {
		setTitle("Controle de vendas");
		setForeground(Color.WHITE);
		setBackground(new Color(255, 255, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				// carrega a data atual do sistema
				Date agora = new Date();
				SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");

				String dataformatada = dataBr.format(agora);

				textData.setText(dataformatada);
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 997, 638);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Define as máscara

		MaskFormatter mascaraCPF = null;

		try {

			mascaraCPF = new MaskFormatter("###.###.###-##");

		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		JPanel painel_carrinho_de_compras = new JPanel();
		painel_carrinho_de_compras.setBounds(10, 147, 968, 338);
		contentPane.add(painel_carrinho_de_compras);
		painel_carrinho_de_compras.setToolTipText("");
		painel_carrinho_de_compras.setForeground(Color.BLACK);
		painel_carrinho_de_compras.setBorder(null);
		painel_carrinho_de_compras.setBackground(new Color(47, 79, 79));
		painel_carrinho_de_compras.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 948, 293);
		painel_carrinho_de_compras.add(scrollPane);

		tabelaItens = new JTable();
		tabelaItens.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaItens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabelaItens.setBackground(new Color(255, 255, 255));
		tabelaItens.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Produto", "Qtd.", "Preço", "Subtotal" }));
		scrollPane.setViewportView(tabelaItens);

		JButton botao_pagamento = new JButton("Pagamento");

		botao_pagamento.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Botão de pagamento
				Frame_Pagamento telapagamento = new Frame_Pagamento();
				telapagamento.textTotal.setText(String.valueOf(total));

				telapagamento.cliente = obj;
				telapagamento.carrinho = carrinho;

				telapagamento.setVisible(true);
				escontertela();

			}
		});

		botao_pagamento.setBounds(38, 513, 169, 39);
		contentPane.add(botao_pagamento);
		botao_pagamento.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/pagamento.png")));
		botao_pagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton botao_cancelarVenda = new JButton("Cancelar Venda");
		botao_cancelarVenda.setBounds(217, 513, 163, 39);
		contentPane.add(botao_cancelarVenda);
		botao_cancelarVenda.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/excluir.png")));
		botao_cancelarVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JPanel painel_produto = new JPanel();
		painel_produto.setBounds(484, 11, 483, 125);
		contentPane.add(painel_produto);
		painel_produto.setLayout(null);
		painel_produto.setToolTipText("");
		painel_produto.setForeground(Color.BLACK);
		painel_produto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Produto",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painel_produto.setBackground(Color.WHITE);

		JLabel label_Codigo = new JLabel("C\u00F3digo:");
		label_Codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Codigo.setBounds(10, 24, 56, 23);
		painel_produto.add(label_Codigo);

		JLabel label_Produto = new JLabel("Produto:");
		label_Produto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Produto.setBounds(200, 28, 56, 14);
		painel_produto.add(label_Produto);

		JLabel lblQtd = new JLabel("Qtd:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 69, 35, 19);
		painel_produto.add(lblQtd);

		textProduto = new JTextField();
		textProduto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textProduto.setBackground(Color.WHITE);
		textProduto.setText("\r\n");
		textProduto.setToolTipText("Produto");
		textProduto.setColumns(10);
		textProduto.setBounds(201, 42, 271, 20);
		painel_produto.add(textProduto);

		textQuantidade = new JTextField();
		textQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textQuantidade.setText("");
		textQuantidade.setColumns(10);
		textQuantidade.setBounds(10, 87, 155, 20);
		painel_produto.add(textQuantidade);

		textCodigo = new JTextField();

		textCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					Produtos obj = new Produtos();
					ProdutosDAO dao = new ProdutosDAO();

					obj = dao.ConsultaProdutosPorCodigo(Integer.parseInt(textCodigo.getText()));

					textProduto.setText(obj.getDescricao());
					textPreco.setText(String.valueOf(obj.getPreco()));
				}
			}
		});

		textCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCodigo.setText("");
		textCodigo.setColumns(10);
		textCodigo.setBounds(10, 42, 155, 20);
		painel_produto.add(textCodigo);

		JLabel label_Preco = new JLabel("Pre\u00E7o:");
		label_Preco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Preco.setBounds(200, 67, 56, 23);
		painel_produto.add(label_Preco);

		textPreco = new JTextField();
		textPreco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPreco.setText("");
		textPreco.setColumns(10);
		textPreco.setBounds(201, 87, 95, 20);
		painel_produto.add(textPreco);

		JButton botao_pesquisar = new JButton("");
		botao_pesquisar.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/search.png")));
		botao_pesquisar.setOpaque(false);
		botao_pesquisar.setContentAreaFilled(false);
		botao_pesquisar.setBorderPainted(false);

		botao_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produtos obj = new Produtos();
				ProdutosDAO dao = new ProdutosDAO();

				obj = dao.ConsultaProdutosPorCodigo(Integer.parseInt(textCodigo.getText()));

				textProduto.setText(obj.getDescricao());
				textPreco.setText(String.valueOf(obj.getPreco()));

			}

		});
		botao_pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botao_pesquisar.setBounds(165, 38, 28, 23);
		painel_produto.add(botao_pesquisar);

		JButton botao_adicionar_item = new JButton("Adicionar item");
		botao_adicionar_item.setOpaque(false);
		botao_adicionar_item.setContentAreaFilled(false);
		botao_adicionar_item.setBorderPainted(false);

		botao_adicionar_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DecimalFormat formato = new DecimalFormat("##.##");

				// botao Adicionar item
				quantidade = Integer.parseInt(textQuantidade.getText());
				preco = Double.parseDouble(textPreco.getText());

				subtotal = quantidade * preco;

				total += subtotal;
				total = Double.valueOf(formato.format(total).replace(",", "."));

				textTotal.setText(String.valueOf(total));

				// Adicionar o produto no carrinho
				carrinho = (DefaultTableModel) tabelaItens.getModel();

				carrinho.addRow(new Object[] { textCodigo.getText(), textProduto.getText(), textQuantidade.getText(),
						textPreco.getText(), subtotal });
			}
		});

		botao_adicionar_item.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/novo.png")));
		botao_adicionar_item.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botao_adicionar_item.setBounds(306, 69, 164, 41);
		painel_produto.add(botao_adicionar_item);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(412, 492, 555, 86);
		contentPane.add(panel_1);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setLayout(null);

		JLabel label_total = new JLabel("TOTAL DA VENDA:");
		label_total.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_total.setBounds(10, 25, 157, 39);
		panel_1.add(label_total);

		textTotal = new JTextField();
		textTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal.setForeground(new Color(0, 0, 0));
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 55));
		textTotal.setText("");
		textTotal.setColumns(10);
		textTotal.setBounds(177, 11, 368, 61);
		panel_1.add(textTotal);

		JPanel painel_clientes = new JPanel();
		painel_clientes.setBounds(22, 11, 440, 125);
		contentPane.add(painel_clientes);
		painel_clientes.setForeground(new Color(0, 0, 0));
		painel_clientes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Cliente",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painel_clientes.setToolTipText("");
		painel_clientes.setBackground(new Color(255, 255, 255));
		painel_clientes.setLayout(null);
		JLabel label_CPF = new JLabel("CPF:");
		label_CPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_CPF.setBounds(8, 26, 30, 14);
		painel_clientes.add(label_CPF);

		JFormattedTextField textCPF = new JFormattedTextField(mascaraCPF);
		textCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// busca cliente por cpf
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					ClientesDAO dao = new ClientesDAO();

					obj = dao.buscarClientePorCPF(textCPF.getText());

					textNome.setText(obj.getNome());
				}
			}
		});
		textCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCPF.setBounds(8, 40, 153, 20);
		painel_clientes.add(textCPF);

		JLabel label_Nome = new JLabel("Nome:");
		label_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Nome.setBounds(8, 65, 46, 14);
		painel_clientes.add(label_Nome);

		JLabel label_Data = new JLabel("Data:");
		label_Data.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Data.setBounds(198, 26, 46, 14);
		painel_clientes.add(label_Data);

		textNome = new JTextField();
		textNome.setToolTipText("");
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNome.setBounds(8, 83, 304, 20);
		painel_clientes.add(textNome);
		textNome.setColumns(10);

		textData = new JTextField();
		textData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setEditable(false);
		textData.setText("");
		textData.setBounds(199, 40, 113, 20);
		painel_clientes.add(textData);
		textData.setColumns(10);

		JButton botao_pesquisar_cliente = new JButton("");
		botao_pesquisar_cliente.setOpaque(false);
		botao_pesquisar_cliente.setContentAreaFilled(false);
		botao_pesquisar_cliente.setBorderPainted(false);
		botao_pesquisar_cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				ClientesDAO dao = new ClientesDAO();

				obj = dao.buscarClientePorCPF(textCPF.getText());

				textNome.setText(obj.getNome());
			}
		});
		botao_pesquisar_cliente.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/search.png")));
		botao_pesquisar_cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botao_pesquisar_cliente.setBounds(160, 37, 30, 23);
		painel_clientes.add(botao_pesquisar_cliente);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Frame_Vendas.class.getResource("/imagens/cliente64px.png")));
		lblNewLabel.setBounds(352, 26, 64, 77);
		painel_clientes.add(lblNewLabel);

	}
}
