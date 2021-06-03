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
import pacote.dao.ClientesDAO;
import pacote.dao.FornecedoresDAO;
import pacote.model.Clientes;
import pacote.model.Fornecedores;
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
import javax.swing.border.EtchedBorder;

public class Frame_Fornecedores extends JFrame {

	private JPanel contentPane;

	private JTable tabelaFornecedores;

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Fornecedores frame = new Frame_Fornecedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo Listar na Tabela
	public void listar() {

		FornecedoresDAO dao = new FornecedoresDAO();
		List<Fornecedores> lista = dao.listarFornecedores();
		DefaultTableModel dados = (DefaultTableModel) tabelaFornecedores.getModel();
		dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

		for (Fornecedores c : lista) {
			// pra cada linha vai adicionarum novo vetor de objetos
			dados.addRow(new Object[] { c.getId(), c.getNome(), c.getCnpj(), c.getEmail(), c.getTelefone(),
					c.getCelular(), c.getCep(), c.getEndereco(), c.getNumero(), c.getComplemento(), c.getBairro(),
					c.getCidade(), c.getUf() });

		}

	}

	/**
	 * Criação do frame.
	 */
	public Frame_Fornecedores() {
		setBackground(new Color(255, 255, 255));
		setTitle("Controle de Fornecedores");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				listar();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 924, 510);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 918, 62);
		panel_top.setBackground(new Color(0, 139, 139));
		contentPane.add(panel_top);
		panel_top.setLayout(null);

		JLabel label_CadastroFornecedores = new JLabel("Cadastro de Fornecedores");
		label_CadastroFornecedores.setForeground(Color.WHITE);
		label_CadastroFornecedores.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_CadastroFornecedores.setBounds(305, 11, 285, 29);
		panel_top.add(label_CadastroFornecedores);

		/* Inserindo Painel com guias */

		JTabbedPane panel_guia = new JTabbedPane(JTabbedPane.TOP);
		panel_guia.setBackground(new Color(255, 255, 255));
		panel_guia.setBounds(0, 62, 918, 317);
		contentPane.add(panel_guia);

		/* ***************** Implementação da PAINEL Clientes ************************/

		JPanel painel_consulta = new JPanel();
		painel_consulta.setBackground(new Color(255, 255, 255));
		panel_guia.addTab("Consulta de Fornecedores", null, painel_consulta, null);

		/* ***************** Implementação da guia1 ************************/

		JPanel painel_dados = new JPanel();
		painel_dados.setBackground(new Color(255, 255, 255));
		panel_guia.addTab("Dados Pessoais", null, painel_dados, null);
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
		textCodigo.setBounds(64, 8, 94, 22);
		painel_dados.add(textCodigo);

		/* Inserindo rotulo e caixa de texto "Nome" na guia 1 */
		JLabel label_Nome = new JLabel("Nome:");
		label_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Nome.setBounds(10, 45, 46, 14);
		painel_dados.add(label_Nome);

		JTextArea textNome = new JTextArea();
		textNome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textNome.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textNome.setBounds(64, 41, 389, 22);
		painel_dados.add(textNome);

		/* Inserindo rotulo e caixa de texto "E-mail" na guia 1 */
		JLabel label_Email = new JLabel("E-mail:");
		label_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Email.setBounds(10, 78, 46, 14);
		painel_dados.add(label_Email);

		JTextArea textEmail = new JTextArea();
		textEmail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textEmail.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textEmail.setBounds(64, 74, 389, 22);
		painel_dados.add(textEmail);

		/* Inserindo rotulo e caixa de texto "Endereço" na guia 1 */
		JLabel label_Endereco = new JLabel("Endere\u00E7o:");
		label_Endereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Endereco.setBounds(209, 113, 78, 14);
		painel_dados.add(label_Endereco);

		JTextArea textEndereco = new JTextArea();
		textEndereco.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textEndereco.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textEndereco.setBounds(274, 107, 389, 22);
		painel_dados.add(textEndereco);

		/* Inserindo rotulo e caixa de texto "Nº" na guia 1 */
		JLabel label_Numero = new JLabel("N\u00BA:");
		label_Numero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Numero.setBounds(32, 144, 21, 14);
		painel_dados.add(label_Numero);

		JTextArea textNumero = new JTextArea();
		textNumero.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textNumero.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textNumero.setBounds(63, 140, 48, 22);
		painel_dados.add(textNumero);

		// Define as máscaras
		MaskFormatter mascaraCep = null;
		MaskFormatter mascaraTelefone = null;
		MaskFormatter mascaraCPF = null;
		MaskFormatter mascaraCelular = null;
		MaskFormatter mascaraCNPJ = null;

		try {
			mascaraCep = new MaskFormatter("#####-###");
			mascaraTelefone = new MaskFormatter("(##)####-####");
			mascaraCPF = new MaskFormatter("#########-##");
			mascaraCelular = new MaskFormatter("(##)# ####-####");
			mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
			mascaraCep.setPlaceholderCharacter('_');
			mascaraTelefone.setPlaceholderCharacter('_');
			mascaraCPF.setPlaceholderCharacter('_');
			mascaraCelular.setPlaceholderCharacter('_');
			mascaraCNPJ.setPlaceholderCharacter('_');
		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		/* Inserindo rotulo e caixa de texto "Celular" na guia 1 */
		JLabel label_Celular = new JLabel("Celular:");
		label_Celular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Celular.setBounds(463, 80, 48, 14);
		painel_dados.add(label_Celular);

		JFormattedTextField textCelular = new JFormattedTextField(mascaraCelular);
		textCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCelular.setBounds(521, 75, 142, 22);
		painel_dados.add(textCelular);

		/* Inserindo rotulo e caixa de texto "Telefone" na guia 1 */
		JLabel label_Telefone = new JLabel("Telefone:");
		label_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Telefone.setBounds(668, 78, 62, 14);
		painel_dados.add(label_Telefone);

		JFormattedTextField textTelefone = new JFormattedTextField(mascaraCelular);
		textTelefone.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTelefone.setBounds(740, 74, 152, 22);
		painel_dados.add(textTelefone);

		/* Inserindo rotulo e caixa de texto "CEP" na guia 1 */
		JLabel label_CEP = new JLabel("CEP:");
		label_CEP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_CEP.setBounds(20, 111, 30, 14);
		painel_dados.add(label_CEP);

		JFormattedTextField textCEP = new JFormattedTextField(mascaraCep);
		textCEP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCEP.setBounds(64, 109, 135, 20);
		painel_dados.add(textCEP);

		/* Inserindo rotulo e caixa de texto "Bairro" na guia 1 */
		JLabel label_Bairro = new JLabel("Bairro:");
		label_Bairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Bairro.setBounds(673, 111, 46, 14);
		painel_dados.add(label_Bairro);

		JTextArea textBairro = new JTextArea();
		textBairro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textBairro.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textBairro.setBounds(715, 108, 177, 22);
		painel_dados.add(textBairro);

		/* Inserindo rotulo e caixa de texto "Cidade" na guia 1 */
		JLabel label_Cidade = new JLabel("Cidade:");
		label_Cidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Cidade.setBounds(121, 146, 46, 14);
		painel_dados.add(label_Cidade);

		JTextArea textCidade = new JTextArea();
		textCidade.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCidade.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textCidade.setBounds(177, 141, 230, 22);
		painel_dados.add(textCidade);

		/* Inserindo rotulo e caixa de texto "Complemento" na guia 1 */
		JLabel label_Complemento = new JLabel("Complemento");
		label_Complemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Complemento.setBounds(426, 146, 94, 14);
		painel_dados.add(label_Complemento);

		JTextArea textComplemento = new JTextArea();
		textComplemento.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textComplemento.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textComplemento.setBounds(521, 142, 230, 22);
		painel_dados.add(textComplemento);

		/* Inserindo rotulo e lista "UF" na guia 1 */
		JLabel label_UF = new JLabel("UF:");
		label_UF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_UF.setBounds(755, 146, 21, 14);
		painel_dados.add(label_UF);

		JComboBox comboBox_UF = new JComboBox();
		comboBox_UF.setBackground(new Color(255, 255, 255));
		comboBox_UF.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		comboBox_UF.setModel(new DefaultComboBoxModel(
				new String[] { "RJ", "SP", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
						"PA", "PB", "PR", "PE", "PI", "RN", "RS", "RO", "RR", "SC", "SE", "TO" }));
		comboBox_UF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_UF.setBounds(786, 143, 48, 20);
		painel_dados.add(comboBox_UF);

		/* Inserindo rotulo e caixa de texto "RG" na guia 1 */
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(18, 182, 40, 14);
		painel_dados.add(lblCnpj);

		JFormattedTextField textCNPJ = new JFormattedTextField(mascaraCNPJ);
		textCNPJ.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCNPJ.setBounds(64, 181, 174, 20);
		painel_dados.add(textCNPJ);

		JButton button_Salvar = new JButton("SALVAR");
		button_Salvar.setIcon(new ImageIcon(Frame_Fornecedores.class.getResource("/imagens/salvar.png")));
		button_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Salvar.setBounds(280, 407, 111, 34);
		contentPane.add(button_Salvar);

		button_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Fornecedores obj = new Fornecedores();

				obj.setNome(textNome.getText());
				obj.setCnpj(textCNPJ.getText());
				obj.setEmail(textEmail.getText());
				obj.setTelefone(textTelefone.getText());
				obj.setCelular(textCelular.getText());
				obj.setCep(textCEP.getText());
				obj.setEndereco(textEndereco.getText());
				obj.setNumero(Integer.parseInt(textNumero.getText()));
				obj.setComplemento(textComplemento.getText());
				obj.setBairro(textBairro.getText());
				obj.setCidade(textCidade.getText());
				obj.setUf(comboBox_UF.getSelectedItem().toString());

				FornecedoresDAO dao = new FornecedoresDAO();

				dao.cadastrarFornecedores(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Editar = new JButton("EDITAR");
		button_Editar.setIcon(new ImageIcon(Frame_Fornecedores.class.getResource("/imagens/editar.png")));
		button_Editar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Editar.setBounds(401, 407, 111, 34);
		contentPane.add(button_Editar);

		button_Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao editar
				Fornecedores obj = new Fornecedores();

				obj.setNome(textNome.getText());
				obj.setCnpj(textCNPJ.getText());
				obj.setTelefone(textTelefone.getText());
				obj.setCelular(textCelular.getText());
				obj.setCep(textCEP.getText());
				obj.setEndereco(textEndereco.getText());
				obj.setNumero(Integer.parseInt(textNumero.getText()));
				obj.setComplemento(textComplemento.getText());
				obj.setBairro(textBairro.getText());
				obj.setCidade(textCidade.getText());
				obj.setUf(comboBox_UF.getSelectedItem().toString());

				obj.setId(Integer.parseInt(textCodigo.getText()));

				FornecedoresDAO dao = new FornecedoresDAO();

				dao.alterarFornecedores(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Excluir = new JButton("EXCLUIR");
		button_Excluir.setIcon(new ImageIcon(Frame_Fornecedores.class.getResource("/imagens/excluir.png")));
		button_Excluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Excluir.setBounds(522, 407, 119, 34);
		contentPane.add(button_Excluir);

		button_Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao excluir
				Fornecedores obj = new Fornecedores();

				obj.setId(Integer.parseInt(textCodigo.getText()));

				FornecedoresDAO dao = new FornecedoresDAO();

				dao.excluirFornedores(obj);

				new Utilitarios().limparCampos(painel_dados);
			}
		});

		/***********************************************
		 * Guia2
		 **************************************************************/

		JLabel label_Nome2 = new JLabel("Nome:");
		label_Nome2.setBackground(new Color(255, 255, 255));
		label_Nome2.setForeground(new Color(0, 0, 0));
		label_Nome2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JTextArea textPesquisaNome = new JTextArea();
		textPesquisaNome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		// Atualiza a lista enquanto digita
		textPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String nome = "%" + textPesquisaNome.getText() + "%";

				FornecedoresDAO dao = new FornecedoresDAO();
				List<Fornecedores> lista = dao.buscarFornecedoresPorNome(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaFornecedores.getModel();
				dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

				for (Fornecedores c : lista) {
					// pra cada linha vai adicionarum novo vetor de objetos
					dados.addRow(new Object[] { c.getId(), c.getNome(), c.getCnpj(), c.getEmail(),
							c.getTelefone(), c.getCelular(), c.getCep(), c.getEndereco(), c.getNumero(),
							c.getComplemento(), c.getBairro(), c.getCidade(), c.getUf() });
				}

			}
		});
		textPesquisaNome.setFont(new Font("Monospaced", Font.PLAIN, 14));

		JButton button_Pesquisar = new JButton("Pesquisar");
		button_Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao pesquisar
				String nome = "%" + textPesquisaNome.getText() + "%";

				FornecedoresDAO dao = new FornecedoresDAO();
				List<Fornecedores> lista = dao.buscarFornecedoresPorNome(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaFornecedores.getModel();
				dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

				for (Fornecedores c : lista) {
					// pra cada linha vai adicionarum novo vetor de objetos
					dados.addRow(new Object[] { c.getId(), c.getNome(), c.getCnpj(), c.getEmail(),
							c.getTelefone(), c.getCelular(), c.getCep(), c.getEndereco(), c.getNumero(),
							c.getComplemento(), c.getBairro(), c.getCidade(), c.getUf() });
				}

			}
		});

		button_Pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_painel_consulta = new GroupLayout(painel_consulta);
		gl_painel_consulta.setHorizontalGroup(
			gl_painel_consulta.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_painel_consulta.createSequentialGroup()
					.addContainerGap(326, Short.MAX_VALUE)
					.addComponent(label_Nome2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
		);
		gl_painel_consulta.setVerticalGroup(
			gl_painel_consulta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel_consulta.createSequentialGroup()
					.addGroup(gl_painel_consulta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addGap(23)
							.addComponent(label_Nome2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painel_consulta.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_painel_consulta.createParallelGroup(Alignment.BASELINE)
								.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
		);

		/* Inserindo tabela na guia2 */

		tabelaFornecedores = new JTable();
		tabelaFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Pega os dados
				panel_guia.setSelectedIndex(1);

				textCodigo.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 0).toString());
				textNome.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 1).toString());
				textCNPJ.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 2).toString());
				textEmail.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 4).toString());
				textTelefone.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 5).toString());
				textCelular.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 6).toString());
				textCEP.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 7).toString());
				textEndereco.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 8).toString());
				textNumero.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 9).toString());
				textComplemento.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 10).toString());
				textBairro.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 11).toString());
				textCidade.setText(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 12).toString());
				comboBox_UF.setSelectedItem(tabelaFornecedores.getValueAt(tabelaFornecedores.getSelectedRow(), 13).toString());

			}
		});
		tabelaFornecedores.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código", "Nome", "CNPJ",
				"E-mail", "Telefone", "Celular", "Cep", "Endereço", "Nº", "Comp", "Bairro", "Cidade", "UF" }));
		scrollPane.setViewportView(tabelaFornecedores);
		painel_consulta.setLayout(gl_painel_consulta);

	}
}
