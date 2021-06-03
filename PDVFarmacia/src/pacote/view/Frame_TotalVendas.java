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

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Frame_TotalVendas extends JFrame {

	private JPanel contentPane;
	private JTextField textTotaldavenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_TotalVendas frame = new Frame_TotalVendas();
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
	public Frame_TotalVendas() {
		setTitle("Hist\u00F3rico de Vendas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 272);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBounds(0, 0, 621, 67);
		panel_top.setLayout(null);
		panel_top.setBackground(new Color(0, 128, 128));
		contentPane.add(panel_top);

		JLabel lblTotalDeV = new JLabel("Total de vendas por data");
		lblTotalDeV.setForeground(Color.WHITE);
		lblTotalDeV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTotalDeV.setBounds(170, 21, 277, 29);
		panel_top.add(lblTotalDeV);

		JPanel painel_consulta_por_data = new JPanel();
		painel_consulta_por_data.setForeground(Color.WHITE);
		painel_consulta_por_data.setBounds(0, 73, 621, 170);
		painel_consulta_por_data.setToolTipText("Consulta por Data");
		painel_consulta_por_data.setBackground(new Color(245, 245, 245));
		contentPane.add(painel_consulta_por_data);
		painel_consulta_por_data.setLayout(null);

		JLabel lblDataVenda = new JLabel("Data da venda:");
		lblDataVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataVenda.setBounds(111, 37, 97, 26);
		painel_consulta_por_data.add(lblDataVenda);

		// Define as máscaras
		MaskFormatter mascaradata = null;

		try {
			mascaradata = new MaskFormatter("##/##/####");

		} catch (ParseException erro) {
			System.err.println("Erro na formatação: " + erro.getMessage());
			System.exit(-1);
		}

		JFormattedTextField textData = new JFormattedTextField(mascaradata);
		textData.setFont(new Font("Tahoma", Font.BOLD, 13));
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setBounds(216, 39, 135, 26);
		painel_consulta_por_data.add(textData);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBackground(new Color(192, 192, 192));
		btnConsultar.setBounds(379, 39, 99, 26);
		painel_consulta_por_data.add(btnConsultar);
		
		JLabel lblTotaDaVenda = new JLabel("Total da venda:");
		lblTotaDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotaDaVenda.setBounds(111, 93, 104, 26);
		painel_consulta_por_data.add(lblTotaDaVenda);
		
		textTotaldavenda = new JTextField();
		textTotaldavenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		textTotaldavenda.setForeground(new Color(0, 0, 0));
		textTotaldavenda.setHorizontalAlignment(SwingConstants.CENTER);
		textTotaldavenda.setBounds(216, 95, 135, 26);
		painel_consulta_por_data.add(textTotaldavenda);
		textTotaldavenda.setColumns(10);
		painel_consulta_por_data.add(textTotaldavenda);

		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botão calcular total de venda por data
				try {
					//Receber a data
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate data_venda = LocalDate.parse(textData.getText(), formato);
				 
					double total_venda;
					VendasDAO dao = new VendasDAO();
					total_venda = dao.retornaTotalVendaPorData(data_venda);
				 
					textTotaldavenda.setText(String.valueOf(total_venda));
				 
				} catch(Exception erro){
					
					JOptionPane.showMessageDialog(null, "Informe uma data corretamente");
				}
			
			}

		});

		
		
	}
}
