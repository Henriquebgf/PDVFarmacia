package pacote.dao;

import pacote.jdbc.ConnectionFactory;
import pacote.model.Fornecedores;
import pacote.model.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProdutosDAO {

	private Connection con;

	public ProdutosDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

	public void cadastrarProdutos(Produtos obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id)" + "values(?,?,?,?)";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getDescricao());
			stmt.setDouble(2, obj.getPreco());
			stmt.setInt(3, obj.getQtd_estoque());

			stmt.setInt(4, obj.getFornecedores().getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	public void alterarProdutos(Produtos obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getDescricao());
			stmt.setDouble(2, obj.getPreco());
			stmt.setInt(3, obj.getQtd_estoque());

			stmt.setInt(4, obj.getFornecedores().getId());

			stmt.setInt(5, obj.getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	public void excluirProdutos(Produtos obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "delete from tb_produtos where id = ?";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, obj.getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	/************* Metodo Listar Clientes **************/

	public List<Produtos> listarProdutos() {
		try {
			// 1 passo criar a lista
			List<Produtos> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select p.id, p.descricao, p.preco,p.qtd_estoque, f.nome from tb_produtos as p "
					+ "inner join tb_fornecedores as f on (p.for_id = f.id)";

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Produtos obj = new Produtos();// cria um novo objeto
				Fornecedores f = new Fornecedores();

				obj.setId(rs.getInt("p.id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

				f.setNome(rs.getString(("f.nome")));

				obj.setFornecedores(f);

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	/************* Metodo buscar Produtos **************/

	public List<Produtos> buscarProdutosPorNome(String nome) {
		try {
			// 1 passo criar a lista
			List<Produtos> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select p.id, p.descricao, p.preco,p.qtd_estoque, f.nome from tb_produtos as p "
					+ "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Produtos obj = new Produtos();// cria um novo objeto
				Fornecedores f = new Fornecedores();

				obj.setId(rs.getInt("p.id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

				f.setNome(rs.getString(("f.nome")));

				obj.setFornecedores(f);

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	/************* Metodo Consulta Produtos por código **************/

	public Produtos ConsultaProdutosPorCodigo(int id) {
		try {

			// 1 passo criar o comando SQLe executar
			String sql = "select *from tb_produtos where id= ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql
			Produtos obj = new Produtos();// cria um novo objeto

			if (rs.next()) {

				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setDescricao(rs.getString("descricao"));
				obj.setPreco(rs.getDouble("preco"));
				obj.setQtd_estoque(rs.getInt("qtd_estoque"));

			}

			return obj;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	// Metodo que da baixa no estoque

	public void baixaEstoque(int id, int qtd_nova) {
		try {

			String sql = "update tb_produtos set qtd_estoque= ? where id=?";
			// 2 passo - conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, qtd_nova);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();

			// JOptionPane.showMessageDialog(null,"Produto Alterado com Sucesso!")

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro:" + erro);
		}
	}

	// Metodo que retorna o estoque atual do produto
	public int retornaEstoqueAtual(int id) {
		try {
			int qtd_estoque = 0;

			String sql = "SELECT qtd_estoque from tb_produtos where id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				qtd_estoque = (rs.getInt("qtd_estoque"));
			}
			
			return qtd_estoque;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adicionarEstoque(int id, int qtd_nova) {
		try {

			String sql = "update tb_produtos set qtd_estoque= ? where id=?";
			// 2 passo - conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, qtd_nova);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();

			// JOptionPane.showMessageDialog(null,"Produto Alterado com Sucesso!")

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro:" + erro);
		}
	}

}
