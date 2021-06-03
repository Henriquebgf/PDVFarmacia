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
import pacote.model.Clientes;
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
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class Frame_Cliente extends JFrame {

	private JPanel contentPane;

	private JTable tabelaClientes;

	public static void main(String[] args) {
	
		
		try {
		    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            javax.swing.UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
		    java.util.logging.Logger.getLogger(Frame_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Cliente frame = new Frame_Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo Listar na Tabela
	public void listar() {

		ClientesDAO dao = new ClientesDAO();
		List<Clientes> lista = dao.listarClientes();
		DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();
		dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

		for (Clientes c : lista) {
			// pra cada linha vai adicionarum novo vetor de objetos
			dados.addRow(new Object[] { c.getId(), c.getNome(), c.getRg(), c.getCpf(), c.getEmail(), c.getTelefone(),
					c.getCelular(), c.getCep(), c.getEndereco(), c.getNumero(), c.getComplemento(), c.getBairro(),
					c.getCidade(), c.getUf() });

		}

	}

	/**
	 * Criação do frame.
	 */
	public Frame_Cliente() {
		setResizable(false);
		setTitle("Controle de Clientes");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				listar();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 924, 510);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 918, 63);
		panel_top.setBackground(new Color(0, 139, 139));
		contentPane.add(panel_top);
		panel_top.setLayout(null);

		JLabel label_CadastroClientes = new JLabel("Cadastro de Clientes");
		label_CadastroClientes.setForeground(Color.WHITE);
		label_CadastroClientes.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_CadastroClientes.setBounds(331, 11, 218, 29);
		panel_top.add(label_CadastroClientes);

		/* Inserindo Painel com guias */

		JTabbedPane panel_guia = new JTabbedPane(JTabbedPane.TOP);
		panel_guia.setBackground(new Color(47, 79, 79));
		panel_guia.setBounds(0, 82, 918, 317);
		contentPane.add(panel_guia);

		/* ***************** Implementação da PAINEL Clientes ************************/

		/* ***************** Implementação da guia1 ************************/

		JPanel painel_dados = new JPanel();
		painel_dados.setBorder(new CompoundBorder());
		painel_dados.setBackground(Color.WHITE);
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
		textCodigo.setBackground(Color.LIGHT_GRAY);
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
		textNome.setBackground(Color.WHITE);
		textNome.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textNome.setBounds(64, 41, 389, 28);
		painel_dados.add(textNome);

		/* Inserindo rotulo e caixa de texto "E-mail" na guia 1 */
		JLabel label_Email = new JLabel("E-mail:");
		label_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Email.setBounds(10, 78, 46, 14);
		painel_dados.add(label_Email);

		JTextArea textEmail = new JTextArea();
		textEmail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textEmail.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textEmail.setBounds(64, 74, 389, 28);
		painel_dados.add(textEmail);

		/* Inserindo rotulo e caixa de texto "Endereço" na guia 1 */
		JLabel label_Endereco = new JLabel("Endere\u00E7o:");
		label_Endereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Endereco.setBounds(209, 113, 78, 14);
		painel_dados.add(label_Endereco);

		JTextArea textEndereco = new JTextArea();
		textEndereco.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textEndereco.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textEndereco.setBounds(274, 107, 389, 28);
		painel_dados.add(textEndereco);

		/* Inserindo rotulo e caixa de texto "Nº" na guia 1 */
		JLabel label_Numero = new JLabel("N\u00BA:");
		label_Numero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Numero.setBounds(32, 144, 21, 14);
		painel_dados.add(label_Numero);

		JTextArea textNumero = new JTextArea();
		textNumero.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textNumero.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textNumero.setBounds(63, 140, 48, 28);
		painel_dados.add(textNumero);

		// Define as máscaras
		MaskFormatter mascaraCep = null;
		MaskFormatter mascaraTelefone = null;
		MaskFormatter mascaraCPF = null;
		MaskFormatter mascaraCelular = null;
		MaskFormatter mascaraRG = null;

		try {
			mascaraCep = new MaskFormatter("#####-###");
			mascaraTelefone = new MaskFormatter("(##)####-####");
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCelular = new MaskFormatter("(##)# ####-####");
			mascaraRG = new MaskFormatter("##.###.###-##");
			mascaraCep.setPlaceholderCharacter('_');
			mascaraTelefone.setPlaceholderCharacter('_');
			mascaraCPF.setPlaceholderCharacter('_');
			mascaraCelular.setPlaceholderCharacter('_');
			mascaraRG.setPlaceholderCharacter('_');
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
		textCelular.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCelular.setBounds(521, 69, 142, 27);
		painel_dados.add(textCelular);

		/* Inserindo rotulo e caixa de texto "Telefone" na guia 1 */
		JLabel label_Telefone = new JLabel("Telefone:");
		label_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Telefone.setBounds(668, 78, 62, 14);
		painel_dados.add(label_Telefone);

		JFormattedTextField textTelefone = new JFormattedTextField(mascaraCelular);
		textTelefone.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTelefone.setBounds(740, 71, 152, 25);
		painel_dados.add(textTelefone);

		/* Inserindo rotulo e caixa de texto "CEP" na guia 1 */
		JLabel label_CEP = new JLabel("CEP:");
		label_CEP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_CEP.setBounds(20, 111, 30, 14);
		painel_dados.add(label_CEP);

		JFormattedTextField textCEP = new JFormattedTextField(mascaraCep);
		textCEP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCEP.setBounds(64, 109, 135, 26);
		painel_dados.add(textCEP);

		/* Inserindo rotulo e caixa de texto "Bairro" na guia 1 */
		JLabel label_Bairro = new JLabel("Bairro:");
		label_Bairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Bairro.setBounds(673, 111, 46, 14);
		painel_dados.add(label_Bairro);

		JTextArea textBairro = new JTextArea();
		textBairro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textBairro.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textBairro.setBounds(715, 108, 177, 27);
		painel_dados.add(textBairro);

		/* Inserindo rotulo e caixa de texto "Cidade" na guia 1 */
		JLabel label_Cidade = new JLabel("Cidade:");
		label_Cidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Cidade.setBounds(121, 146, 46, 14);
		painel_dados.add(label_Cidade);

		JTextArea textCidade = new JTextArea();
		textCidade.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCidade.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textCidade.setBounds(177, 141, 230, 29);
		painel_dados.add(textCidade);

		/* Inserindo rotulo e caixa de texto "Complemento" na guia 1 */
		JLabel label_Complemento = new JLabel("Complemento");
		label_Complemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Complemento.setBounds(426, 146, 94, 14);
		painel_dados.add(label_Complemento);

		JTextArea textComplemento = new JTextArea();
		textComplemento.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textComplemento.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textComplemento.setBounds(521, 142, 230, 26);
		painel_dados.add(textComplemento);

		/* Inserindo rotulo e lista "UF" na guia 1 */
		JLabel label_UF = new JLabel("UF:");
		label_UF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_UF.setBounds(755, 146, 21, 14);
		painel_dados.add(label_UF);

		JComboBox comboBox_UF = new JComboBox();
		comboBox_UF.setAutoscrolls(true);
		comboBox_UF.setModel(new DefaultComboBoxModel(
				new String[] { "RJ", "SP", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
						"PA", "PB", "PR", "PE", "PI", "RN", "RS", "RO", "RR", "SC", "SE", "TO" }));
		comboBox_UF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_UF.setBounds(786, 143, 48, 25);
		painel_dados.add(comboBox_UF);

		/* Inserindo rotulo e caixa de texto "RG" na guia 1 */
		JLabel label_RG = new JLabel("RG:");
		label_RG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_RG.setBounds(32, 181, 30, 14);
		painel_dados.add(label_RG);

		JFormattedTextField textRG = new JFormattedTextField(mascaraRG);
		textRG.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textRG.setBounds(58, 180, 174, 28);
		painel_dados.add(textRG);

		/* Inserindo rotulo e caixa de texto "CPF" na guia 1 */
		JLabel label_CPF = new JLabel("CPF:");
		label_CPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_CPF.setBounds(242, 183, 30, 14);
		painel_dados.add(label_CPF);

		JFormattedTextField textCPF = new JFormattedTextField(mascaraCPF);
		textCPF.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textCPF.setBounds(284, 180, 181, 28);
		painel_dados.add(textCPF);

		JButton button_Salvar = new JButton("SALVAR");
		button_Salvar.setIcon(new ImageIcon(Frame_Cliente.class.getResource("/imagens/salvar.png")));
		button_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Salvar.setBounds(280, 410, 111, 41);
		contentPane.add(button_Salvar);

		button_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Clientes obj = new Clientes();

				obj.setNome(textNome.getText());
				obj.setRg(textRG.getText());
				obj.setCpf(textCPF.getText());
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

				ClientesDAO dao = new ClientesDAO();

				dao.cadastrarCliente(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Editar = new JButton("EDITAR");
		button_Editar.setIcon(new ImageIcon(Frame_Cliente.class.getResource("/imagens/editar.png")));
		button_Editar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Editar.setBounds(401, 410, 111, 41);
		contentPane.add(button_Editar);

		button_Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao editar
				Clientes obj = new Clientes();

				obj.setNome(textNome.getText());
				obj.setRg(textRG.getText());
				obj.setCpf(textCPF.getText());
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

				obj.setId(Integer.parseInt(textCodigo.getText()));

				ClientesDAO dao = new ClientesDAO();

				dao.alterarCliente(obj);

				new Utilitarios().limparCampos(painel_dados);

			}
		});

		JButton button_Excluir = new JButton("EXCLUIR");
		button_Excluir.setIcon(new ImageIcon(Frame_Cliente.class.getResource("/imagens/excluir.png")));
		button_Excluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_Excluir.setBounds(522, 410, 119, 41);
		contentPane.add(button_Excluir);

		button_Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao excluir
				Clientes obj = new Clientes();

				obj.setId(Integer.parseInt(textCodigo.getText()));

				ClientesDAO dao = new ClientesDAO();

				dao.excluirCliente(obj);

				new Utilitarios().limparCampos(painel_dados);
			}
		});

		/***********************************************
		 * Guia2
		 **************************************************************/

		/* Inserindo tabela na guia2 */
		
				JPanel painel_consulta = new JPanel();
				painel_consulta.setBackground(Color.WHITE);
				panel_guia.addTab("Consulta de Clientes", null, painel_consulta, null);
				
						JLabel label_Nome2 = new JLabel("Nome:");
						label_Nome2.setBackground(Color.WHITE);
						label_Nome2.setForeground(Color.BLACK);
						label_Nome2.setFont(new Font("Tahoma", Font.PLAIN, 15));
						
								JTextArea textPesquisaNome = new JTextArea();
								textPesquisaNome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
								textPesquisaNome.setBackground(Color.WHITE);
								
										// Atualiza a lista enquanto digita
										textPesquisaNome.addKeyListener(new KeyAdapter() {
											@Override
											public void keyPressed(KeyEvent e) {
												String nome = "%" + textPesquisaNome.getText() + "%";
								
												ClientesDAO dao = new ClientesDAO();
												List<Clientes> lista = dao.buscarClientePorNome(nome);
												DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();
												dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada
								
												for (Clientes c : lista) {
													// pra cada linha vai adicionarum novo vetor de objetos
													dados.addRow(new Object[] { c.getId(), c.getNome(), c.getRg(), c.getCpf(), c.getEmail(),
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

														ClientesDAO dao = new ClientesDAO();
														List<Clientes> lista = dao.buscarClientePorNome(nome);
														DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();
														dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

														for (Clientes c : lista) {
															// pra cada linha vai adicionarum novo vetor de objetos
															dados.addRow(new Object[] { c.getId(), c.getNome(), c.getRg(), c.getCpf(), c.getEmail(),
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
																		.addGroup(gl_painel_consulta.createSequentialGroup()
																			.addContainerGap()
																			.addGroup(gl_painel_consulta.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_painel_consulta.createSequentialGroup()
																					.addComponent(label_Nome2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
																					.addGap(6)
																					.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
																					.addGap(353))
																				.addGroup(Alignment.TRAILING, gl_painel_consulta.createSequentialGroup()
																					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
																					.addContainerGap())))
																);
																gl_painel_consulta.setVerticalGroup(
																	gl_painel_consulta.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_painel_consulta.createSequentialGroup()
																			.addGap(20)
																			.addGroup(gl_painel_consulta.createParallelGroup(Alignment.BASELINE)
																				.addComponent(textPesquisaNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(button_Pesquisar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
																				.addComponent(label_Nome2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
																			.addGap(19)
																			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
																);
																
																		tabelaClientes = new JTable();
																		tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
																		tabelaClientes.setFont(new Font("Tahoma", Font.PLAIN, 11));
																		tabelaClientes.setForeground(new Color(0, 0, 0));
																		tabelaClientes.setFillsViewportHeight(true);
																		tabelaClientes.setBackground(new Color(255, 255, 255));
																		tabelaClientes.addMouseListener(new MouseAdapter() {
																			@Override
																			public void mouseClicked(MouseEvent e) {
																				// Pega os dados
																				panel_guia.setSelectedIndex(1);

																				textCodigo.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 0).toString());
																				textNome.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 1).toString());
																				textRG.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2).toString());
																				textCPF.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3).toString());
																				textEmail.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 4).toString());
																				textTelefone.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 5).toString());
																				textCelular.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 6).toString());
																				textCEP.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 7).toString());
																				textEndereco.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 8).toString());
																				textNumero.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 9).toString());
																				textComplemento.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 10).toString());
																				textBairro.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 11).toString());
																				textCidade.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 12).toString());
																				comboBox_UF.setSelectedItem(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 13).toString());

																			}
																		});
																		tabelaClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código", "Nome", "RG", "CPf",
																				"E-mail", "Telefone", "Celular", "Cep", "Endereço", "Nº", "Comp", "Bairro", "Cidade", "UF" }));
																		scrollPane.setViewportView(tabelaClientes);
																		painel_consulta.setLayout(gl_painel_consulta);

	}
}
