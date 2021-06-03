package pacote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pacote.dao.ItemvendaDAO;
import pacote.dao.ProdutosDAO;
import pacote.dao.VendasDAO;
import pacote.model.Clientes;
import pacote.model.ItemVenda;
import pacote.model.Produtos;
import pacote.model.Vendas;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class Frame_Pagamento extends JFrame {

	private JPanel contentPane;
	private JTextField textDinheiro;
	private JTextField textCartao;
	private JTextField textCheque;
	private JTextField textTroco;
	public JTextField textTotal;

	Clientes cliente = new Clientes();
	DefaultTableModel carrinho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					System.out.println("Error setting native LAF: " + e);
				}
				try {
					Frame_Pagamento frame = new Frame_Pagamento();
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
	public Frame_Pagamento() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 467);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_pagamento = new JPanel();
		panel_pagamento.setBackground(new Color(0, 128, 128));
		panel_pagamento.setBounds(0, 0, 734, 80);
		contentPane.add(panel_pagamento);
		panel_pagamento.setLayout(null);

		JLabel lblpagamento = new JLabel("Pagamentos");
		lblpagamento.setForeground(new Color(255, 255, 255));
		lblpagamento.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblpagamento.setBounds(312, 21, 139, 34);
		panel_pagamento.add(lblpagamento);

		textDinheiro = new JTextField();
		textDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textDinheiro.setBounds(195, 95, 156, 31);
		contentPane.add(textDinheiro);
		textDinheiro.setColumns(10);

		textCartao = new JTextField();
		textCartao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textCartao.setColumns(10);
		textCartao.setBounds(195, 140, 156, 31);
		contentPane.add(textCartao);

		textCheque = new JTextField();
		textCheque.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textCheque.setColumns(10);
		textCheque.setBounds(195, 184, 156, 31);
		contentPane.add(textCheque);

		textTroco = new JTextField();
		textTroco.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textTroco.setEditable(false);
		textTroco.setColumns(10);
		textTroco.setBounds(195, 226, 156, 31);
		contentPane.add(textTroco);

		textTotal = new JTextField();
		textTotal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(195, 268, 156, 31);
		contentPane.add(textTotal);

		JTextPane textObs = new JTextPane();
		textObs.setBackground(new Color(255, 255, 255));
		textObs.setForeground(Color.WHITE);
		textObs.setBounds(394, 140, 293, 159);
		contentPane.add(textObs);

		JLabel lblDinheiro = new JLabel("DINHEIRO:");
		lblDinheiro.setForeground(new Color(0, 0, 0));
		lblDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinheiro.setBounds(86, 91, 99, 31);
		contentPane.add(lblDinheiro);

		textCheque.setText("0");
		textCartao.setText("0");
		textDinheiro.setText("0");
		textTroco.setText("0");

		JLabel lblCartao = new JLabel("CART\u00C3O:");
		lblCartao.setForeground(new Color(0, 0, 0));
		lblCartao.setHorizontalAlignment(SwingConstants.LEFT);
		lblCartao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCartao.setBounds(86, 136, 99, 31);
		contentPane.add(lblCartao);

		JLabel lblCheque = new JLabel("CHEQUE:");
		lblCheque.setForeground(new Color(0, 0, 0));
		lblCheque.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheque.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCheque.setBounds(86, 180, 99, 30);
		contentPane.add(lblCheque);

		JLabel lblTroco = new JLabel("TROCO:");
		lblTroco.setForeground(new Color(0, 0, 0));
		lblTroco.setHorizontalAlignment(SwingConstants.LEFT);
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTroco.setBounds(86, 222, 99, 30);
		contentPane.add(lblTroco);

		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setForeground(new Color(0, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotal.setBounds(86, 264, 99, 31);
		contentPane.add(lblTotal);

		JButton botaoFinalizarVenda = new JButton("Finalizar Venda");
		botaoFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DecimalFormat formato = new DecimalFormat("##.##");

				double partecartao, partecheque, troco, partedinheiro, totalpago, totalvenda;

				partecartao = Double.parseDouble(textCartao.getText());
				partecheque = Double.parseDouble(textCheque.getText());
				partedinheiro = Double.parseDouble(textDinheiro.getText());
				totalvenda = Double.parseDouble(textTotal.getText());

				// Calcular o troco
				totalpago = partecartao + partecheque + partedinheiro;

				troco = totalpago - totalvenda;

				textTroco.setText(String.valueOf(troco));

				Vendas objv = new Vendas();
				// Dados do cliente (cliente_id)
				objv.setCliente(cliente);

				// Pega a data da venda e obs
				Date agora = new Date();
				SimpleDateFormat dataBr = new SimpleDateFormat("yyyy-MM-dd");
				String datamysql = dataBr.format(agora);

				objv.setData_venda(datamysql);

				// Total da venda
				objv.setTotal_venda(totalvenda);
				objv.setObs(textObs.getText());

				VendasDAO dao_v = new VendasDAO();
				dao_v.cadastrarVenda(objv);

				// Retornar da ultima Venda realizada
				objv.setId(dao_v.retornaUltimaVenda());

				// cadastrando os produtos da tabela itemvendas
				for (int i = 0; i < carrinho.getRowCount(); i++) {

					int qtd_estoque, qtd_comprada, qtd_atualizada;
					Produtos objp = new Produtos();
					ProdutosDAO dao_produto = new ProdutosDAO();

					ItemVenda item = new ItemVenda();
					item.setVenda(objv);

					objp.setId(Integer.parseInt(carrinho.getValueAt(i, 0).toString()));
					item.setProduto(objp);
					item.setQtd(Integer.parseInt(carrinho.getValueAt(i, 2).toString()));
					item.setSubtotal(Double.parseDouble(carrinho.getValueAt(i, 4).toString()));

					// Baixa no Estoque
					qtd_estoque = dao_produto.retornaEstoqueAtual(objp.getId());
					qtd_comprada = Integer.parseInt(carrinho.getValueAt(i, 2).toString());
					qtd_atualizada = qtd_estoque - qtd_comprada;

					dao_produto.baixaEstoque(objp.getId(), qtd_atualizada);

					ItemvendaDAO daoitem = new ItemvendaDAO();
					daoitem.cadastraitem(item);

				}
				/***********************************************************************/
				JOptionPane.showMessageDialog(null, "Venda Registrada com sucesso!");

			}

		});

		botaoFinalizarVenda.setBackground(new Color(255, 255, 255));
		botaoFinalizarVenda.setForeground(new Color(0, 0, 0));
		botaoFinalizarVenda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoFinalizarVenda.setBounds(272, 353, 201, 31);
		contentPane.add(botaoFinalizarVenda);

		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setBackground(Color.WHITE);
		lblObservacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservacoes.setForeground(Color.BLACK);
		lblObservacoes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblObservacoes.setBounds(394, 97, 125, 31);
		contentPane.add(lblObservacoes);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(607, 326, 117, 92);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Frame_Pagamento.class.getResource("/imagens/pay128px.png")));
	}
}
