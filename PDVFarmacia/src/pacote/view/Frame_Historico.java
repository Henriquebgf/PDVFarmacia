package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

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

import pacote.dao.ItemvendaDAO;
import pacote.dao.VendasDAO;
import pacote.model.ItemVenda;
import pacote.model.Produtos;
import pacote.model.Vendas;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;

public class Frame_Historico extends JFrame {

	private JPanel contentPane;
	private JTable tabela_historicoVendas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Historico frame = new Frame_Historico();
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
	public Frame_Historico() {
		setTitle("Hist\u00F3rico de Vendas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 933, 507);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 918, 74);
		panel_top.setLayout(null);
		panel_top.setBackground(new Color(0, 128, 128));
		contentPane.add(panel_top);

		JLabel label_HistoricodeVendas = new JLabel("Hist\u00F3rico de vendas");
		label_HistoricodeVendas.setForeground(Color.WHITE);
		label_HistoricodeVendas.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_HistoricodeVendas.setBounds(329, 22, 237, 29);
		panel_top.add(label_HistoricodeVendas);

		JPanel painel_consulta_por_data = new JPanel();
		painel_consulta_por_data.setForeground(Color.WHITE);
		painel_consulta_por_data.setBounds(0, 73, 918, 110);
		painel_consulta_por_data.setBorder(
				new TitledBorder(null, "Consulta por Data", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		painel_consulta_por_data.setToolTipText("Consulta por Data");
		painel_consulta_por_data.setBackground(new Color(255, 255, 255));
		contentPane.add(painel_consulta_por_data);
		painel_consulta_por_data.setLayout(null);

		JLabel label_DataFiscal = new JLabel("Data inicial:");
		label_DataFiscal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_DataFiscal.setBounds(184, 38, 74, 26);
		painel_consulta_por_data.add(label_DataFiscal);

		// Define as máscaras
		MaskFormatter mascaradata = null;

		try {
			mascaradata = new MaskFormatter("##/##/####");

		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		JFormattedTextField textDataInicio = new JFormattedTextField(mascaradata);
		textDataInicio.setBounds(258, 40, 135, 26);
		painel_consulta_por_data.add(textDataInicio);

		JLabel label_DataFim = new JLabel("Data Fim:");
		label_DataFim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_DataFim.setBounds(402, 38, 74, 26);
		painel_consulta_por_data.add(label_DataFim);

		JFormattedTextField textDataFim = new JFormattedTextField(mascaradata);
		textDataFim.setBounds(470, 40, 135, 26);
		painel_consulta_por_data.add(textDataFim);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botão buscar venda por data

				try {
					// receber as datas
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					LocalDate data_inicio = LocalDate.parse(textDataInicio.getText(), formato);

					LocalDate data_fim = LocalDate.parse(textDataFim.getText(), formato);

					VendasDAO dao = new VendasDAO();
					List<Vendas> lista = dao.listarvendasPorPeriodo(data_inicio, data_fim);

					DefaultTableModel dados = (DefaultTableModel) tabela_historicoVendas.getModel();
					dados.setNumRows(0);

					for (Vendas v : lista) {
						// pra cada linha vai adicionarum novo vetor de objetos
						dados.addRow(new Object[] { v.getId(), v.getData_venda(), v.getCliente().getNome(),
								v.getTotal_venda(), v.getObs() });

					}

					tabela_historicoVendas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							// A coluna do status é 3
							Object ref = table.getValueAt(row, 3);// Coluna Status
							// Coloca cor em todas as linhas,COLUNA(3) que tem o valor !=null
							if (ref != null) {// Se Status for igual a "Aberto"
								setBackground(new Color(0, 128, 128));// Preenche a linha de vermelho
								setForeground(Color.WHITE);// E a fonte de branco
							} else {
								boolean sel = isSelected;
								if (sel == true) {
									setBackground(getBackground());
									setForeground(getForeground());
								} else {// Se Status não for "Aberto"
									setBackground(Color.WHITE);// Preenche a linha de branco
									setForeground(new Color(51, 51, 51));// E a fonte de preto
								}
							}
							return this;
						}
					});

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Digite duas datas como intervalo");

				}
			}

		});

		btnPesquisar.setBackground(new Color(192, 192, 192));
		btnPesquisar.setBounds(615, 40, 99, 26);
		painel_consulta_por_data.add(btnPesquisar);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 150, 918, 318);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 898, 244);
		panel.add(scrollPane);

		tabela_historicoVendas = new JTable();
		tabela_historicoVendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Clicar em uma venda
				Frame_DetalheVenda tela = new Frame_DetalheVenda();

				tela.textCliente.setText(
						tabela_historicoVendas.getValueAt(tabela_historicoVendas.getSelectedRow(), 2).toString());
				tela.texTotaldaVenda.setText(
						tabela_historicoVendas.getValueAt(tabela_historicoVendas.getSelectedRow(), 3).toString());
				tela.textData.setText(
						tabela_historicoVendas.getValueAt(tabela_historicoVendas.getSelectedRow(), 1).toString());
				tela.textAreaObservacoes.setText(
						tabela_historicoVendas.getValueAt(tabela_historicoVendas.getSelectedRow(), 4).toString());

				int venda_id = Integer.parseInt(
						tabela_historicoVendas.getValueAt(tabela_historicoVendas.getSelectedRow(), 0).toString());

				// Dados dos itens comprados
				ItemVenda item = new ItemVenda();
				ItemvendaDAO dao_item = new ItemvendaDAO();
				List<ItemVenda> listaitens = dao_item.listarItemPorVenda(venda_id);

				DefaultTableModel dados = (DefaultTableModel) tela.tabelaItensVendido.getModel();
				dados.setNumRows(0); // limpa osdados para garantir que nao tenha nada

				for (ItemVenda c : listaitens) {
					// pra cada linha vai adicionarum novo vetor de objetos
					dados.addRow(new Object[] {

							c.getProduto().getDescricao(), c.getQtd(), c.getProduto().getPreco(), c.getSubtotal() });
				}

				tela.setVisible(true);
			}
		});
		tabela_historicoVendas.setBackground(new Color(238, 232, 170));
		tabela_historicoVendas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Data da Venda", "Cliente", "Total da Venda", "Obs" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane.setViewportView(tabela_historicoVendas);
	}
}
