package pacote.dao;

import pacote.jdbc.ConnectionFactory;
import pacote.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClientesDAO {

	private Connection con;

	public ClientesDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

	public void cadastrarCliente(Clientes obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setInt(9, obj.getNumero());
			stmt.setString(10, obj.getComplemento());
			stmt.setString(11, obj.getBairro());
			stmt.setString(12, obj.getCidade());
			stmt.setString(13, obj.getUf());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	public void alterarCliente(Clientes obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,"
					+ "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setInt(9, obj.getNumero());
			stmt.setString(10, obj.getComplemento());
			stmt.setString(11, obj.getBairro());
			stmt.setString(12, obj.getCidade());
			stmt.setString(13, obj.getUf());

			stmt.setInt(14, obj.getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	public void excluirCliente(Clientes obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "delete from tb_clientes where id = ?";

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

	public List<Clientes> listarClientes() {
		try {
			// 1 passo criar a lista
			List<Clientes> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select*from tb_clientes";
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Clientes obj = new Clientes();// cria um novo objeto

				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("estado"));

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	/************* Metodo buscar Clientes **************/

	public List<Clientes> buscarClientePorNome(String nome) {
		try {
			// 1 passo criar a lista
			List<Clientes> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select*from tb_clientes where nome like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Clientes obj = new Clientes();// cria um novo objeto

				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("estado"));

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	/************* Metodo buscar Clientes por CPF **************/
	public Clientes buscarClientePorCPF(String cpf) {
		try {
			

			// 1 passo criar o comando SQLe executar
			String sql = "select * from tb_clientes where cpf = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql
			Clientes obj = new Clientes();
			
			if(rs.next()) {
			
				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("estado"));

				
			}

			return obj;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado " );
			return null;
		}
	}


}
