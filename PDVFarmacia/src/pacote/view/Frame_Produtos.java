package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;
import pacote.dao.FornecedoresDAO;
import pacote.dao.ProdutosDAO;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
import javax.swing.border.EtchedBorder;

public class Frame_Produtos extends JFrame {

	public JPanel contentPane;

	public JTable tabelaProdutos;
	public JPanel painel_consulta;

	
	public static void main(String[] args) {
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
			}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Produtos frame = new Frame_Produtos();
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
		DefaultTableModel dados = (DefaultTableModel) tabelaProdutos.getModel();
		dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

		for (Produtos p : lista) {
			// pra cada linha vai adicionarum novo vetor de objetos
			dados.addRow(new Object[] {
					p.getId(), 
					p.getDescricao(),
					p.getPreco(),
					p.getQtd_estoque(),
					p.getFornecedores().getNome()
			});

		}

	}

	/**
	 * Criação do frame.
	 */
	public Frame_Produtos() {
		setTitle("Controle de Produtos");
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				listar();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 924, 485);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 918, 56);
		panel_top.setBackground(new Color(0, 139, 139));
		contentPane.add(panel_top);
		panel_top.setLayout(null);

		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setForeground(Color.WHITE);
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCadastroDeProdutos.setBounds(338, 11, 237, 29);
		panel_top.add(lblCadastroDeProdutos);

		/* Inserindo Painel com guias */

		JTabbedPane panel_guia = new JTabbedPane(JTabbedPane.TOP);
		panel_guia.setBackground(new Color(245, 245, 245));
		panel_guia.setBounds(0, 53, 918, 336);
		contentPane.add(panel_guia);

		/* ***************** Implementação da PAINEL Clientes ************************/

		painel_consulta = new JPanel();
		painel_consulta.setBackground(new Color(245, 245, 245));
		panel_guia.addTab("Consulta de Produtos", null, painel_consulta, null);

		/* ***************** Implementação da guia1 ************************/

		JPanel painel_dados = new JPanel();
		painel_dados.setBackground(new Color(255, 255, 255));
		panel_guia.addTab("Dados de Produtos", null, painel_dados, null);
		painel_dados.setLayout(null);

		/* Inserindo rotulo e caixa de texto "Codigo" na guia1 */
		JLabel label_Codigo = new JLabel("C\u00F3digo:");
		label_Codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Codigo.setBounds(10, 12, 48, 18);
		painel_dados.add(label_Codigo);

		JTextArea textCodigo = new JTextArea();
		textCodigo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCodigo.setEditable(false);
		textCodigo.setForeground(Color.BLACK);
		textCodigo.setBackground(Color.WHITE);
		textCodigo.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textCodigo.setBounds(86, 10, 94, 22);
		painel_dados.add(textCodigo);

		/* Inserindo rotulo e caixa de texto "Nome" na guia 1 */
		JLabel label_descricao = new JLabel("Descri\u00E7\u00E3o:");
		label_descricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_descricao.setBounds(10, 41, 64, 18);
		painel_dados.add(label_descricao);

		JTextArea textDescricao = new JTextArea();
		textDescricao.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textDescricao.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textDescricao.setBounds(86, 39, 389, 22);
		painel_dados.add(textDescricao);

		/* Inserindo rotulo e caixa de texto "E-mail" na guia 1 */
		JLabel label_Preco = new JLabel("Pre\u00E7o:");
		label_Preco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Preco.setBounds(10, 74, 46, 18);
		painel_dados.add(label_Preco);

		JTextArea textPreco = new JTextArea();
		textPreco.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textPreco.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textPreco.setBounds(86, 72, 94, 22);
		painel_dados.add(textPreco);

		
		

		/* Inserindo rotulo e caixa de texto "Celular" na guia 1 */
		JLabel lblQtdestoque = new JLabel("Qtd.Estoque:");
		lblQtdestoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtdestoque.setBounds(188, 74, 92, 18);
		painel_dados.add(lblQtdestoque);

		JTextArea textQuantidade = new JTextArea();
		textQuantidade.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textQuantidade.setText("");
		textQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textQuantidade.setBounds(290, 72, 40, 22);
		painel_dados.add(textQuantidade);

		/* Inserindo rotulo e lista "UF" na guia 1 */
		JLabel lblFornecedores = new JLabel("Fornecedores:");
		lblFornecedores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFornecedores.setBounds(10, 110, 90, 14);
		painel_dados.add(lblFornecedores);

		JComboBox comboBox_Fornecedores = new JComboBox();
		comboBox_Fornecedores.addAncestorListener(new AncestorListener() {
			//executa após a ativação do componente
			public void ancestorAdded(AncestorEvent event) {
				FornecedoresDAO dao = new FornecedoresDAO();
				List<Fornecedores>listadefornecedores = dao.listarFornecedores();
				
				comboBox_Fornecedores.removeAll();// remove os itens do combobox antes de listar.
				
				for(Fornecedores f: listadefornecedores){
					comboBox_Fornecedores.addItem(f);
				}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		comboBox_Fornecedores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_Fornecedores.setBounds(110, 107, 149, 20);
		painel_dados.add(comboBox_Fornecedores);

		JButton button_Salvar = new JButton("SALVAR");
		button_Salvar.setIcon(new ImageIcon(Frame_Produtos.class.getResource("/imagens/salvar.png")));
		button_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Salvar.setBounds(280, 400, 111, 34);
		contentPane.add(button_Salvar);

		button_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produtos obj = new Produtos();

				obj.setDescricao(textDescricao.getText());
				obj.setPreco(Double.parseDouble(textPreco.getText()));
				obj.setQtd_estoque(Integer.parseInt(textQuantidade.getText()));
				
				//Criar um objeto de Fornecedor
				Fornecedores f = new Fornecedores();
				f=(Fornecedores)comboBox_Fornecedores.getSelectedItem();
				obj.setFornecedores(f);

				ProdutosDAO dao = new ProdutosDAO();

				dao.cadastrarProdutos(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Editar = new JButton("EDITAR");
		button_Editar.setIcon(new ImageIcon(Frame_Produtos.class.getResource("/imagens/editar.png")));
		button_Editar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Editar.setBounds(401, 400, 111, 34);
		contentPane.add(button_Editar);
		
		//******** Botao editar *********
		
		button_Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produtos obj = new Produtos();
				obj.setId(Integer.parseInt(textCodigo.getText()));
				obj.setDescricao(textDescricao.getText());
				obj.setPreco(Double.parseDouble(textPreco.getText()));
				obj.setQtd_estoque(Integer.parseInt(textQuantidade.getText()));
				

				//Criar um objeto do tipo Fornecedor
				Fornecedores f= new Fornecedores();
				f= (Fornecedores)comboBox_Fornecedores.getSelectedItem();
				obj.setFornecedores(f);

				ProdutosDAO dao = new ProdutosDAO();

				dao.alterarProdutos(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Excluir = new JButton("EXCLUIR");
		button_Excluir.setIcon(new ImageIcon(Frame_Produtos.class.getResource("/imagens/excluir.png")));
		button_Excluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Excluir.setBounds(522, 400, 119, 34);
		contentPane.add(button_Excluir);

		button_Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao excluir
				Produtos obj = new Produtos();

				obj.setId(Integer.parseInt(textCodigo.getText()));

				ProdutosDAO dao = new ProdutosDAO();

				dao.excluirProdutos(obj);

				new Utilitarios().limparCampos(painel_dados);
			}
		});

		/***********************************************
		 * Guia2
		 **************************************************************/

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setForeground(new Color(0, 0, 0));
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JTextArea textPesquisaNome = new JTextArea();
		textPesquisaNome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		// Atualiza a lista enquanto digita
		textPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String nome = "%" + textPesquisaNome.getText() + "%";

				ProdutosDAO dao = new ProdutosDAO();
				List<Produtos> lista = dao.buscarProdutosPorNome(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaProdutos.getModel();
				dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

				for (Produtos p : lista) {
					// pra cada linha vai adicionarum novo vetor de objetos
					dados.addRow(new Object[] {
							p.getId(), 
							p.getDescricao(),
							p.getPreco(),
							p.getQtd_estoque(),
							p.getFornecedores().getNome() });
				}

			}
		});  
		textPesquisaNome.setFont(new Font("Monospaced", Font.PLAIN, 14));

		JButton button_Pesquisar = new JButton("Pesquisar"); 
		button_Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao pesquisar
				String nome = "%" + textPesquisaNome.getText() + "%";

				ProdutosDAO dao = new ProdutosDAO();
				List<Produtos> lista = dao.buscarProdutosPorNome(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaProdutos.getModel();
				dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

				for (Produtos p : lista) {
					// pra cada linha vai adicionarum novo vetor de objetos
					dados.addRow(new Object[] {
							p.getId(), 
							p.getDescricao(),
							p.getPreco(),
							p.getQtd_estoque(),
							p.getFornecedores().getNome() });
				}

			}
		}); 

		button_Pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_painel_consulta = new GroupLayout(painel_consulta);
		gl_painel_consulta.setHorizontalGroup(
			gl_painel_consulta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel_consulta.createSequentialGroup()
					.addGroup(gl_painel_consulta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addGap(322)
							.addComponent(lblDescrio)
							.addGap(6)
							.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 895, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_painel_consulta.setVerticalGroup(
			gl_painel_consulta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel_consulta.createSequentialGroup()
					.addGroup(gl_painel_consulta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_painel_consulta.createParallelGroup(Alignment.BASELINE)
								.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addGap(23)
							.addComponent(lblDescrio)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addContainerGap())
		);

		/* Inserindo tabela na guia2 */

		tabelaProdutos = new JTable();
		tabelaProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabelaProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Pega os dados
				panel_guia.setSelectedIndex(1);

				textCodigo.setText(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 0).toString());
				textDescricao.setText(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 1).toString());
				textPreco.setText(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 2).toString());
				textQuantidade.setText(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 3).toString());
				
				Fornecedores f = new Fornecedores();
				FornecedoresDAO dao = new FornecedoresDAO();
				f= dao.consultaPorNome(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 4).toString());
				
				comboBox_Fornecedores.removeAllItems();
				comboBox_Fornecedores.getModel().setSelectedItem(f);
				

			}
		});
		tabelaProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "descrição", "Preço", "Qtd.Estoque", "Fornecedor" }));
		scrollPane.setViewportView(tabelaProdutos);
		painel_consulta.setLayout(gl_painel_consulta);

	}
}
