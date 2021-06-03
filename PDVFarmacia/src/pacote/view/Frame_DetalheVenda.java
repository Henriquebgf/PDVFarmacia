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

public class Frame_DetalheVenda extends JFrame {

	private JPanel contentPane;
	public JTable tabelaItensVendido;
	public JTextField textCliente;
	public JTextField texTotaldaVenda;
	public JTextField textData;
	public JTextArea textAreaObservacoes;

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
			java.util.logging.Logger.getLogger(Frame_DetalheVenda.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_DetalheVenda frame = new Frame_DetalheVenda();
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
	public Frame_DetalheVenda() {
		setBackground(Color.WHITE);
		setTitle("Detalhe da Venda");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 933, 595);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 927, 58);
		panel_top.setLayout(null);
		panel_top.setBackground(new Color(0, 128, 128));
		contentPane.add(panel_top);

		JLabel lblDetalheDaVenda = new JLabel("Detalhe da venda");
		lblDetalheDaVenda.setForeground(Color.WHITE);
		lblDetalheDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDetalheDaVenda.setBounds(346, 11, 201, 29);
		panel_top.add(lblDetalheDaVenda);

		JPanel painel_consulta_por_data = new JPanel();
		painel_consulta_por_data.setForeground(Color.WHITE);
		painel_consulta_por_data.setBounds(0, 89, 918, 247);
		painel_consulta_por_data.setBorder(
				new TitledBorder(null, "Consulta por Data", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		painel_consulta_por_data.setToolTipText("Dados da Venda");
		painel_consulta_por_data.setBackground(new Color(255, 255, 255));
		contentPane.add(painel_consulta_por_data);
		painel_consulta_por_data.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCliente.setBounds(61, 34, 50, 26);
		painel_consulta_por_data.add(lblCliente);

		// Define as máscaras
		MaskFormatter mascaradata = null;

		try {
			mascaradata = new MaskFormatter("##/##/####");

		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		JLabel label_Data = new JLabel("Data:");
		label_Data.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Data.setBounds(71, 71, 44, 26);
		painel_consulta_por_data.add(label_Data);

		JLabel label_Observacoes = new JLabel("Observa\u00E7\u00F5es:");
		label_Observacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_Observacoes.setBounds(27, 122, 84, 26);
		painel_consulta_por_data.add(label_Observacoes);

		JLabel label_TotaldaVenda = new JLabel("Total da Venda:");
		label_TotaldaVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_TotaldaVenda.setBounds(468, 34, 102, 26);
		painel_consulta_por_data.add(label_TotaldaVenda);

		textCliente = new JTextField();
		textCliente.setEditable(false);
		textCliente.setBounds(121, 34, 275, 26);
		painel_consulta_por_data.add(textCliente);
		textCliente.setColumns(10);

		texTotaldaVenda = new JTextField();
		texTotaldaVenda.setEditable(false);
		texTotaldaVenda.setColumns(10);
		texTotaldaVenda.setBounds(572, 34, 260, 26);
		painel_consulta_por_data.add(texTotaldaVenda);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setColumns(10);
		textData.setBounds(121, 71, 131, 26);
		painel_consulta_por_data.add(textData);

		textAreaObservacoes = new JTextArea();
		textAreaObservacoes.setBackground(UIManager.getColor("Button.light"));
		textAreaObservacoes.setBounds(121, 139, 711, 80);
		painel_consulta_por_data.add(textAreaObservacoes);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 150, 918, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 193, 898, 196);
		panel.add(scrollPane);

		tabelaItensVendido = new JTable();
		tabelaItensVendido.setBackground(Color.WHITE);
		tabelaItensVendido.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Produto", "Qtd comprada", "Valor", "Subtotal" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane.setViewportView(tabelaItensVendido);
	}
}
