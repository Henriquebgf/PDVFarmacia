package pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pacote.jdbc.ConnectionFactory;
import pacote.model.ItemVenda;
import pacote.model.Produtos;

public class ItemvendaDAO {
	private Connection con;

	public ItemvendaDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

// metodo que cadastra itens
	public void cadastraitem(ItemVenda obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)values(?,?,?,?)";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, obj.getVenda().getId());
			stmt.setInt(2, obj.getProduto().getId());
			stmt.setInt(3, obj.getQtd());
			stmt.setDouble(4, obj.getSubtotal());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	// ******************** Metodo que lista Itens de uma venda por id
	// *************************

	public List<ItemVenda> listarItemPorVenda(int venda_id) {
		try {
			// 1 passo criar a lista
			List<ItemVenda> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select p.descricao,i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
					+ " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, venda_id);

			ResultSet rs = ps.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				ItemVenda item = new ItemVenda();
				Produtos prod = new Produtos();

				prod.setDescricao(rs.getString("p.descricao"));
				item.setQtd(rs.getInt("i.qtd"));
				prod.setPreco(rs.getDouble("p.preco"));
				item.setSubtotal(rs.getInt("i.subtotal"));

				item.setProduto(prod);

				lista.add(item);

			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

}